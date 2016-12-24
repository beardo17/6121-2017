/*jslint node: true */
"use strict";
var logger = document.getElementById("logger");
var loggerText = document.getElementById("logTxt");
var frontCam = document.getElementById("front");
var reaCam = document.getElementById("rear");

var logTxt = "";

function printLog(txt) {
    logTxt += "\r\n" + txt;
    loggerText.innerHTML = logTxt;
}

function switchCam() {
    
    printLog("cams switched");
}
