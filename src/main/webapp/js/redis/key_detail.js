/**
 * Created by Administrator on 2016/10/17.
 */
'use strict';

modelApp.controller('keyModel', function($scope, $rootScope, $http){

    function initkey(){
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

    $scope.hashTrClick = function(entity){
        $scope.hashKey = entity.key;
        $scope.hashValue = entity.value;
        $scope.keyByteSize = stringByteLength($scope.hashKey);
        $scope.valueByteSize = stringByteLength($scope.hashValue);
    }

    //rename modal open
    $scope.openRenameModal = function(){
        $("#Rename").modal("show");
    }

    //rename modal save click function
    $scope.renameSave = function(){
        var params = {};
        params["oldKey"] = $scope.oldKey;
        params["newKey"] = $scope.key;
        $http({
            method: "post",
            url: url + "/keyRename",
            params:params,
            headers: {
                contentType: 'application/json;charset=UTF-8'
            }
        }).success(function (data) {
            if (data.rtnCode == "0000000") {
                if (data.bizData.request == "success"){
                    $("#Rename").modal("hide");
                    $('.modal-backdrop').remove();
                    $scope.oldKey = $scope.key;
                    $rootScope.query();
                }else {
                    alert("Can't rename key: Key with new name already exist in database");
                }
            }
        });
    }

    //计算str的bytesize
    function stringByteLength(str){
        var result = [];
        for(var i = 0; i < str.length; i++){
            result.push(str.charCodeAt(i));
        }
        return result.length;
    }

    initkey();

});