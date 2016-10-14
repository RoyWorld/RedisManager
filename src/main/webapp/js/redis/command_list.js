'use strict';

/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.controller('modelListCtrl', function($scope, $rootScope, $http){

    function init(){
        $http({
            method: "get",
            url: "/redis/command_list",
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