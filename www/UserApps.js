var exec = require('cordova/exec');

exports.getUserApps = function(success, error) {
    exec(success, error, "UserApps", "getUserApps", []);
};
