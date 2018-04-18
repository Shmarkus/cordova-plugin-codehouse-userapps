
exports.defineAutoTests = function() {
  describe('UserApps object existance check', function() {

    it("userapps", function () {
      expect( userapps).toBeDefined();
    });

    it("userapps.getUserApps", function() {
      expect( userapps.getUserApps ).toBeDefined();
    });
  });

  describe('getUserApps call test', function() {

    var value;
    var callbacks;

    beforeEach(function(done) {
      callbacks = {
        win: function(arg){
          value = arg;
          done();
        },
        fail: function(err){
          console.log("callbacks.fail");
          done();
        }
      };

      spyOn(callbacks, 'win').and.callThrough();
      spyOn(callbacks, 'fail').and.callThrough();
      
      userapps.getUserApps("test", callbacks.win, callbacks.fail);
    });

    it("to have been called", function() {
      expect(callbacks.win).toHaveBeenCalled();
    });

    it("check return value", function() {
      expect(value).toBe("test");
    });

  });
};
