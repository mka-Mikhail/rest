angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products').then(function (response) {
            $scope.productList = response.data;
        });
    };

    $scope.deleteProductFromRepoById = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId).then(function (response) {
            $scope.loadProducts();
        });
    };

    $scope.addNewProductToRepo = function () {
        $http.post(contextPath + '/products', $scope.newProduct).then(function (response) {
            $scope.loadProducts();
        });
    };

    $scope.loadProducts();
});