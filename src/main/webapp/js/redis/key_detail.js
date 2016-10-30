/**
 * Created by Administrator on 2016/10/17.
 */
'use strict';

modelApp.controller('keyModel', function($scope, $rootScope, $http){

    function init(){
        $scope.stringShow = false;
        $scope.listShow = false;
        $scope.hashShow = true;
        $scope.setShow = false;
        $scope.zsetShow = false;
        $scope.query();
    }

    //查database中所有keys
    $scope.query = function (){
        //$http({
        //    method: "get",
        //    url: "/redis/manager/database",
        //    headers: {
        //        contentType: 'application/json;charset=UTF-8'
        //    }
        //}).success(function (data) {
        //    if (data.rtnCode == "0000000") {
        //        $scope.databaseList = data.bizData;
        //    }
        //});
    }
    

    init();

});