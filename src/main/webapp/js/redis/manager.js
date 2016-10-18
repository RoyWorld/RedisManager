/**
 * Created by Administrator on 2016/10/17.
 */
'use strict';

/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.controller('modelListCtrl', function($scope, $rootScope, $http){

    function init(){
        $scope.connections = ["connectio", "fiang", "dfina3"];
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