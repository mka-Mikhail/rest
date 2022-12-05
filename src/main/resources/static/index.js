angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app/api/v1';

    $scope.loadProducts = function (indexPage = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
            }
        }).then(function (response) {
            $scope.productsPage = response.data.content;
        });
    };

    $scope.deleteProductFromRepoById = function (productId) {
        $http.delete(contextPath + '/products/delete/' + productId).then(function (response) {
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