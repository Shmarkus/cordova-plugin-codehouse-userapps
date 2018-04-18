var exec = require('cordova/exec');

exports.getUserApps = function(arg0, success, error) {
    exec(success, error, "UserApps", "getUserApps", [arg0]);
};
