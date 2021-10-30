import React, {useState, useEffect}  from "react";
import "./styles.css";
import api from "./api";
import axios from "axios";
import { Line } from "react-chartjs-2";
export default function App() {
  let [tableData, setTableData] = React.useState('')
  console.log('to get data')
  // fetches data
  const fetchData = (e) => {
       axios.get("http://34.127.33.28:8080/temperature/latest", {"Access-Control-Allow-Origin":"*", "Content-Type":
 "application/x-www-form-urlencoded", "Accept":"application/json"})
           .then(response => {
               console.log(response.data);
               console.log(response.data.content);
               let rawData = {};
               var datasets= {};
               var length = response.data.content.length;
               var i = 0; 
               var labels= new Array();
               var targets= new Array();
               var ambients= new Array();
               while (i <length){
                  labels[i] = response.data.content[length-1-i].timeCollected;
                  targets[i] = response.data.content[length-1-i].target;
                  ambients[i] = response.data.content[length-1-i].ambient;
                  i++;
               }
               setTableData({
                       labels: labels,
                       datasets: [
                          {
                             label: "Target Temperatures",
                             data: targets, 
                             fill: true,
                             backgroundColor: "rgba(75, 192, 192, 0.2)",
                             borderColor: "rgba(75, 192, 192, 1)"
                          },
                          {
                             label: "Ambient Temperature",
                             data: ambients,
                             fill: false,
                             borderColor: "#742774"
                          }
                       ]
               });
           }).catch((error) => {
               console.log("error in getting response");
               console.log(error.response);
               console.log(error);
           })
  };
  useEffect(() => {
    fetchData();
  }, [])
  return (
    <div className="App">
          <button onClick={(e) => fetchData(e)} type='button'>Click Me For Latest Data</button>
      <Line data= {tableData}  />
    </div>
  );
}
