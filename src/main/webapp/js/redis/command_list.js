'use strict';

/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.controller('modelListCtrl', function($scope, $rootScope, $http){

    function init(){

        $scope.query();

        //查command的groups
        $http({
            method: "get",
            url: "/redis/command_group",
            headers: {
                contentType: 'application/json;charset=UTF-8'
            }
        }).success(function (data) {
            if (data.rtnCode == "0000000") {
                $scope.commandGroups = data.bizData;
            }
        });
    }

    //查command的list
    $scope.query = function (){
        var params = {};
        if ($scope.commandGroup != null && $scope.commandGroup != ''){
            params["group"] = $scope.commandGroup;
        }
        if ($scope.commandName != null && $scope.commandName != ''){
            params["name"] = $scope.commandName;
        }
        $http({
            method: "get",
            url: "/redis/command_list",
            params:params,
            headers: {
                contentType: 'application/json;charset=UTF-8'
            }
        }).success(function (data) {
            if (data.rtnCode == "0000000") {
                $scope.commandList = data.bizData;
            }
        });
    }

    init();

});