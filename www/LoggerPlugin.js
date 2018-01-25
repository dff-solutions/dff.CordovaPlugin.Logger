/**
 *
 * @author Anthony Nahas
 * @since 25.01.18
 * @version 1.0
 *
 */

const exec = require('cordova/exec');

const FEATURE = "LoggerPlugin";

function LoggerPlugin() {
    console.log("LoggerPlugin.js has been created");
}

module.exports = new LoggerPlugin();