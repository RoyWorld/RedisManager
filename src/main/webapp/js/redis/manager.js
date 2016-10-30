/**
 * Created by Administrator on 2016/10/17.
 */
'use strict';

var url = "/redis/manager";

/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.controller('modelListCtrl', function($scope, $rootScope, $http){

    function init(){
        $scope.connections = ["connectio", "fiang", "dfina3"];
        $scope.query();
    }

    //查database中所有keys
    $scope.query = function (){
        $http({
            method: "get",
            url: url + "/database",
            headers: {
                contentType: 'application/json;charset=UTF-8'
            }
        }).success(function (data) {
            if (data.rtnCode == "0000000") {
                $scope.databaseList = data.bizData;
            }
        });
    }

    //侧边树状信息栏的点击事件
    $scope.treeToggle = function(databaseTree) {
        for (var database in $scope.databaseList){
            if (databaseTree.name == $scope.databaseList[database].name){
                $scope.databaseList[database].display = !$scope.databaseList[database].display;
                break;
            }
        }
    }

    //原始的tree收缩点击事件
    $('.tree-toggle').click(function() {
        $(this).parent().children('ul.tree').toggle(200);
    });

    //tree中key的点击事件
    $scope.keySelect = function (key, databaseName) {
        var params = {};
        params["key"] = key;
        params["databaseName"] = databaseName;
        $http({
            method: "get",
            url: url + "/keySelect",
            params:params,
            headers: {
                contentType: 'application/json;charset=UTF-8'
            }
        }).success(function (data) {
            if (data.rtnCode == "0000000") {
                $scope.key = data.bizData.key;
                $scope.hashmapValue = data.bizData.value;
                $scope.ttl = data.bizData.ttl;
                $scope.size = data.bizData.size;
            }
        });
    }
    
    $(".nav-tabs").on("click", "a", function (e) {
        e.preventDefault();
        if (!$(this).hasClass('add-contact')) {
            $(this).tab('show');
        }
    }).on("click", "span", function () {
        var anchor = $(this).siblings('a');
        $(anchor.attr('href')).remove();
        $(this).parent().remove();
        $(".nav-tabs li").children('a').first().click();
    });

    $('.add-contact').click(function (e) {
        e.preventDefault();
        var id = $(".nav-tabs").children().length; //think about it ;)
        var tabId = 'contact_' + id;
        $(this).closest('li').before('<li><a href="#contact_' + id + '">New Tab</a></li>');
        $('.tab-content').append('<div class="tab-pane" id="' + tabId + '">Contact Form: New Contact ' + id + '</div>');
        $('.nav-tabs li:nth-child(' + id + ') a').click();
    });

    init();

});