var app = angular.module("algolia.addrecipe", []); 
app.controller("myCtrl", function($scope, $http) {
    
    $scope.recipes = [];

    $scope.search = function () {
        
        // clear error:
        $scope.errortext = "";    
        
        // clear recipes list:
        $scope.recipes = [];

        var url = 'https://api.edamam.com/search?app_id=6c5d59a0&app_key=8b5bc454bf95e550121ead8d8e981d88&from=0&to=10'
        url += '&q=' + $scope.ingridents

        $http
        .get(url)            
        // .get('dummy-response.json')
        .then(function successCallback(response) {
            $scope.recipes = response.data.hits;
        });
    }

    $scope.addRecipeToAlgolia = function (recipe) {
        
        alert("TODO: " + recipe.label);
    }
});