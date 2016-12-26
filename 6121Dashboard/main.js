/*jslint node: true */
"use strict";
var logger = document.getElementById("logger");
var loggerText = document.getElementById("logTxt");
var frontCam = document.getElementById("front");
var rearCam = document.getElementById("rear");
var auto1 = document.getElementById("a1");
var auto2 = document.getElementById("a2");
var auto3 = document.getElementById("a3");

var logTxt = "";
var logCount = 0;
var autonMode = "";

function printLog(txt) {
    logTxt += "<br />";
    logTxt += txt;
    loggerText.innerHTML = logTxt;
    logger.scrollTop = logger.scrollHeight;
}

function switchCam() {
    var tempSrc = frontCam.src;
    frontCam.src = rearCam.src;
    rearCam.src = tempSrc;
    printLog("cams switched");
}


auto1.addEventListener("click", function () {
    autonMode = "Auton1";
    printLog("Auton Mode:<span class='red'> Auto 1</span>");
});

auto2.addEventListener("click", function () {
    autonMode = "Auton2";
    printLog("Auton Mode:<span class='red'>  Auto 2</span>");
});

auto3.addEventListener("click", function () {
    autonMode = "Auton3";
    printLog("Auton Mode:<span class='red'>  Auto 3</span>");
});

