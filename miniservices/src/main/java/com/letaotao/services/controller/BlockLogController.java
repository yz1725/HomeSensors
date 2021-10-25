package com.letaotao.services.controller;

import com.letaotao.common.utils.LogUtil;
import com.letaotao.services.handler.BlockLogHandler;
import com.letaotao.services.model.BlockModel;
import com.letaotao.services.request.ReveiewCreateRequest;
import com.letaotao.services.request.ReviewListRequest;
import com.letaotao.services.request.ReviewReadRequest;
import com.letaotao.services.response.HttpBaseResponse;
import com.letaotao.services.response.ReviewCreateResponse;
import com.letaotao.services.response.ReviewListResponse;
import com.letaotao.services.response.ReviewReadResponse;
import com.letaotao.util.AddressVerifier;
import com.letaotao.util.BitSetOperations;
import com.letaotao.util.BitcoinConstants;
import com.letaotao.util.BlockConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;

@Slf4j
@RestController
public class BlockLogController {

    @Autowired
    BlockLogHandler blockLogHandler;

    int block = 1024;
    BigInteger blockSize = new BigInteger("1024");
    BigInteger rangeSize = new BigInteger("1048576"); //1024*1024 = 1MM

    static BigInteger currentIndex = null;
    static BigInteger nextRange = null;

    // key: start, value: results
    static Map<String, BitSet> results = new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger(BlockLogHandler.class);

    static Map<String, String> foundAddress = new HashMap<>();

    /**
     * get the start
     * @param pk
     * @return
     */
    private BigInteger getStart(BigInteger pk){
        if (nextRange==null){
            //todo need to find the range from db
            System.out.println("edge case, need to be aware");
        }
        BigInteger possibleStart = nextRange;
        if(BlockConfig.isDirectionUp()){
            while(possibleStart.compareTo(pk)>0 && possibleStart.compareTo(BigInteger.ZERO)>0){
                possibleStart = possibleStart.subtract(this.rangeSize);
            }
        }else{
            while (possibleStart.compareTo(pk) < 0
                    && possibleStart.compareTo(BitcoinConstants.MAX_PRIVATE_KEY) < 0) {
                    possibleStart = possibleStart.add(this.rangeSize);
            }
        }
        return possibleStart;
    }

    private int getPosition(BigInteger pk, BigInteger start){
        int diff = Math.abs(pk.subtract(start).intValue());
        return (diff / block);
    }


    private synchronized void updateResult(){

    }

    @RequestMapping(method = RequestMethod.POST, value = "/review/echoTest")
    public ResponseEntity<HttpBaseResponse> httpTest(@RequestBody ReveiewCreateRequest request) {
//        request.getId();
        ReviewCreateResponse reviewCreateResponse = new ReviewCreateResponse();
        reviewCreateResponse.setId(request.getId());
        reviewCreateResponse.setResult("SUCCESS");
        reviewCreateResponse.setMsg("SUCCESS");

        return new ResponseEntity(reviewCreateResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/review/create")
    public ResponseEntity<HttpBaseResponse> createReview(@RequestBody ReveiewCreateRequest request) {
//        LogUtil.info(log, "get the incoming request" + request);

//        LOGGER.info("get the incoming request" + request);
        // curl -X POST --data '{"id": 123,"userId": "bbb", "data":["p1:a1:a2", "p2:a2:a2"] }' -H "Content-Type:application/json"  http://localhost:8080/review/create
        // update database result field
        /**
         * figure out the row in the database, and update the bit in the result, update the status if needed.
         */
        System.out.println(request.getData().size());
        BigInteger firstPK = new BigInteger(request.getData().get(0).split(":")[0]);
        BigInteger start = getStart(firstPK);

        // get the results stored till now
        BitSet result = null;
        BlockModel blockModel = null;
        if(results.containsKey(start.toString())){
            result = results.get(start.toString());
        }else{
            blockModel = blockLogHandler.getByStart(start.toString());
            result = BitSet.valueOf(blockModel.getResult());
            System.out.println(BitSetOperations.convertBitSetToBinaryString(result));
        }
        result.set(getPosition(firstPK, start), true);
        // need to decide if we want to save the result or a batch save
        if (blockModel!=null){
            System.out.println(BitSetOperations.convertBitSetToBinaryString(result));
            blockModel.setResult(result.toByteArray());
            blockLogHandler.updateResult(blockModel);
        }

        int j=0;
        for(String s: request.getData()){
            String[] kv=s.split(":");
//            System.out.print(kv[0]+"-");
//            System.out.println(kv[1]+"-");
//            System.out.println(kv[2]);
            // found an address
            if ( AddressVerifier.verifyAddress(kv[1]) || AddressVerifier.verifyAddress(kv[2])){
                LOGGER.info("found key " + kv[0] + " with address "+kv[1]);
                LOGGER.info("found key " + kv[0] + " with address "+kv[2]);
                foundAddress.put(kv[0], kv[1]);
                LogUtil.info(log, "found key " + kv[0] + " with address "+kv[1]);
                LogUtil.info(log, "found key " + kv[0] + " with address "+kv[2]);
                System.out.println("found key " + kv[0] + " with address "+kv[1]);
                System.out.println("found key " + kv[0] + " with address "+kv[2]);
                // write the info to database
                blockLogHandler.saveFoundAddress(kv[0], request.getUserId()+":"+s);

            }
//            j++;
//            if (j==1000){
//                System.out.print(kv[0]+"-");
//                System.out.println(kv[1]+"-"+kv[2]);
//                System.out.print("Total number of address in AddressVerifier has: "+AddressVerifier.getAddressesInfo());
//            }
        }

        ReviewCreateResponse reviewCreateResponse = new ReviewCreateResponse();
        reviewCreateResponse.setId(request.getId());
        reviewCreateResponse.setResult("SUCCESS");
        reviewCreateResponse.setMsg("SUCCESS");

        return new ResponseEntity(reviewCreateResponse, HttpStatus.OK);
    }

    @GetMapping("/review/read")
    public ResponseEntity<HttpBaseResponse> readReview(@RequestBody ReviewReadRequest request) {
        LogUtil.info(log, "get the incoming request");

        ReviewReadResponse reviewReadResponse = new ReviewReadResponse();

        return new ResponseEntity(reviewReadResponse, HttpStatus.OK);
    }

    @GetMapping("/review/list")
    public ResponseEntity<HttpBaseResponse> listReview(@RequestBody(required=false) ReviewListRequest request) {
//        LogUtil.info(log, "get the incoming request");
//        LOGGER.info("get the incoming request");
//        curl -X GET --data '{"id": 123,"userId": "bbb", "type":"block" }' -H "Content-Type:application/json"  http://localhost:8080/review/list

        if (!this.foundAddress.isEmpty()){
            for(String s: this.foundAddress.keySet()){
                System.out.println(s+":"+foundAddress.get(s));
            }
        }
        // need to initialize
        if(currentIndex==null){
            // load from database if necessary
            String latestStart = blockLogHandler.getLatestBlock();

            // latestStart should not be null,  we will init the db
            currentIndex = new BigInteger(latestStart);
            if (BlockConfig.isDirectionUp()){
                currentIndex = currentIndex.add(rangeSize);
                nextRange =currentIndex.add(rangeSize);
            }else{
                currentIndex = currentIndex.subtract(rangeSize);
                nextRange =currentIndex.subtract(rangeSize);
            }
            results.put(currentIndex.toString(), new BitSet(block));
            System.out.println("need to init currentIndex with value: "+currentIndex);
            System.out.println("need to init nextRange with value: "+nextRange);
        }
//        reviewHandler.createReview();
        ReviewListResponse reviewListResponse = new ReviewListResponse();
        reviewListResponse.setBlockSize(block);
        reviewListResponse.setResult("SUCCESS");
        reviewListResponse.setId(request==null?null:request.getId());
        reviewListResponse.setMsg("Get the block to process");

        // we only have one server running
        buildResponse(reviewListResponse);

        System.out.println("current index is " + currentIndex);
        System.out.println("nextRange is " + nextRange);
        return new ResponseEntity(reviewListResponse, HttpStatus.OK);
    }

    /**
     * only one server is running, synchronized is ok for now
     * @param reviewListResponse
     */
    private synchronized void buildResponse(ReviewListResponse reviewListResponse){
        reviewListResponse.setStartIndex(currentIndex.toString());
        if(BlockConfig.isDirectionUp()){
            currentIndex = currentIndex.add(blockSize);
            reviewListResponse.setDirectionUp(true);
            if (currentIndex.compareTo(nextRange)>=0){
                // init the results for this block
//                LOGGER.info("increasing a new block and add a new record: "+currentIndex);
//                System.out.println("increasing a new block and add a new record: "+currentIndex);
//                System.out.println(results.get(nextRange.subtract(rangeSize).toString()));
                results.put(currentIndex.toString(), new BitSet(block));
                // add a new record/row in the table
                blockLogHandler.createBlock(nextRange.subtract(rangeSize).toString(),
                        nextRange.subtract(BigInteger.ONE).toString(), results.get(nextRange.subtract(rangeSize).toString()));

                nextRange = nextRange.add(rangeSize);
            }
        }else{
            // direction is down
            currentIndex = currentIndex.subtract(blockSize);
            reviewListResponse.setDirectionUp(false);
            if (currentIndex.compareTo(nextRange)<=0){
                // init the results for this block
//                LOGGER.info("decrease to a new block and add a new record: "+currentIndex);
//                System.out.println("decrease to a new block and add a new record: "+currentIndex);
//                System.out.println(results.get(nextRange.add(rangeSize).toString()));
                results.put(currentIndex.toString(), new BitSet(block));
                // add a new record/row in the table
                blockLogHandler.createBlock(nextRange.add(rangeSize).toString(),
                        nextRange.add(BigInteger.ONE).toString(), results.get(nextRange.add(rangeSize).toString()));

                nextRange = nextRange.subtract(rangeSize);
            }
        }


    }
}