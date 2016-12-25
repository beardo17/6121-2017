/*jslint node: true */
"use strict";
var logger = document.getElementById("logger");
var loggerText = document.getElementById("logTxt");
var frontCam = document.getElementById("front");
var reaCam = document.getElementById("rear");

var logTxt = "";

function printLog(txt) {
    logTxt += "<br />";
    logTxt += txt;
    loggerText.innerHTML = logTxt;
    logger.scrollTop = logger.scrollHeight;
}

function switchCam() {
    var tempSrc = frontCam.src;
    frontCam.src = reaCam.src;
    reaCam.src = tempSrc;
    printLog("cams switched");
}
