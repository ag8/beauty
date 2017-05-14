"use strict";

let rules = new Map();


let angle = 25;



let variables = getParameterByName("variables").split(",");
setValue("variables", variables);
let constants = getParameterByName("constants").split(",");
setValue("constants", constants);
// let variables = "X,Y";
// let constants = "F,+,-";

// alert(variables);
// alert(constants);

let start = getParameterByName("start");
setValue("start", start);
// let start = "XF";
// alert(start);

let n = getParameterByName("n");
// let n = 8;
n = n - (-1);
n = n - 1; // Damn it Javascript
setValue("n", n);
// alert(n);

rules.set(getParameterByName("rule1from"), getParameterByName("rule1to"));
rules.set(getParameterByName("rule2from"), getParameterByName("rule2to"));

for (let i = 1; i < 3; i++) {
    setValue("rule" + i + "from", getParameterByName("rule" + i + "from"));
    setValue("rule" + i + "to", getParameterByName("rule" + i + "to"));
}

console.log(rules);



function step(current, rules) {
    let letters = current.split('');
    let newString = "";

    for (let i = 0; i < letters.length; i++) {
        let letter = letters[i];

        if (rules.has(letter)) {
            newString += rules.get(letter);
        } else {
            newString += letter;
        }
    }

    return newString;
}

async function run(start, rules, n, animate) {
    let string = start;

    let size = 5;

    function getNewX(x, dir) {
        dir = 90 - dir;
        let angle_rad = dir * Math.PI / 180;
        return x + Math.cos(angle_rad) * size;
    }

    function getNewY(y, dir) {
        dir = 90 - dir;
        let angle_rad = dir * Math.PI / 180;
        return y + Math.sin(angle_rad) * size;
    }

    for (let i = 0; i < n + 1; i++) {
        // alert("i=" + i + ", start");
        // alert(i + "<" + (n+1) + "? " + (i < n + 1) + ".");

        let draw = (i == n); // Only draw on the last iteration

        if (draw === true) {



            // ctx.fillStyle = "#FF0000";
            // ctx.fillRect(0, 0, 800, 800);
            ctx.clearRect(0, 0, 800, 800);
            let x = 1800 / 2;
            let y = 700 / 2;
            let dir = 0;
            ctx.beginPath();
            ctx.moveTo(x, y);

            // Print the string to the log
            console.log(string);

            // Split the string into individual commands
            let letters = string.split('');

            /*

            ctx.strokeStyle = '#0000FF';
            // Draw background fractal
            for (let q = 0; q < letters.length; q++) {
                let letter = letters[q];

                if (letter === "F") { // Forward
                    let newX = getNewX(x, dir);
                    let newY = getNewY(y, dir);

                    ctx.lineTo(newX, newY);
                    ctx.moveTo(newX, newY);
                    ctx.stroke();
                    x = newX;
                    y = newY;
                } else if (letter === "+") {
                    dir++;
                    dir %= 4;
                } else if (letter === "-") {
                    dir += 4;
                    dir--;
                    dir %= 4;
                }
            }

            ctx.closePath();

            */

            // await sleep(10000);

            x = 1800 / 2;
            y = 700 / 2;
            let savedX = x;
            let savedY = y;
            dir = 0;
            let justSkipped = false;
            ctx.beginPath();
            ctx.moveTo(x, y);

            ctx.strokeStyle = '#000000';
            for (let q = 0; q < letters.length; q++) {
                let letter = letters[q];

                console.log("x: " + x + ", y: " + y + ", dir: " + dir + ".");

                if (letter === "F") { // Forward
                    let newX = getNewX(x, dir);
                    let newY = getNewY(y, dir);

                    if (!justSkipped) {
                        ctx.lineTo(Math.floor(newX), Math.floor(newY));
                    }
                    ctx.moveTo(Math.floor(newX), Math.floor(newY));
                    ctx.stroke();
                    x = newX;
                    y = newY;
                } else if (letter === "+") {
                    dir += angle;
                    dir %= 360;
                } else if (letter === "-") {
                    dir += 360;
                    dir -= angle;
                    dir %= 360;
                } else if (letter === "[") {
                    console.log("Saving coordinates");
                    savedX = x;
                    savedY = y;
                } else if (letter === "]") {
                    console.log("Skipping back");
                    x = savedX;
                    y = savedY;
                    justSkipped = true;
                }

                if (letter !== "]") {
                    justSkipped = false;
                }

                if (animate) {
                    await sleep(1);
                }
            }

            ctx.closePath();

        }


        // Finally, update the string for the next step
        string = step(string, rules);

        // alert("i=" + i + ", end");
    }

    console.log(string);

    // alert("Done with async function!");
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function setValue(id, value) {
    document.getElementById(id).value = value;
}


// Based on http://stackoverflow.com/a/901144
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}


const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");

run(start, rules, n, true);

// alert("Done!");