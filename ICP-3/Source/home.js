/**
 * Created by user on 24/02/2016.
 */
var myapp = angular.module( 'homeModule', ['googleOauth','FacebookProvider'] );

myapp.config( function (TokenProvider) {
    // Demo configuration for the "angular-oauth demo" project on Google.
    // Log in at will!

    // Sorry about this way of getting a relative URL, powers that be.

    var baseUrl = document.URL.replace( '/home.html', '' );
    TokenProvider.extendConfig( {
        clientId: '202317690708-062ts2disvkoi7lfm6strp08updu3n45.apps.googleusercontent.com',
        redirectUri: baseUrl + '/home.html',  // allow lunching demo from a mirror
        scopes: ["https://www.googleapis.com/auth/userinfo.email"]
    } );
} );
myapp.controller( 'homeController', function ($scope, $http,$rootScope,$log, $window, Token, Facebook,$http,$location) {
    $scope.accessToken = Token.get()
    //Initializing Array Lists.
    $scope.recipelist = new Array();
    $scope.venueList = new Array();
    $scope.mostRecentReview;
    $scope.findRecipe = function () {
        //On Success
        $http.get( 'https://api.nutritionix.com/v1_1/search/' + $scope.recipe + '?results=0:1&fields=*&appId=7ed6be10&appKey=c96e86eeb3c01904d13ac93cc98776d4' ).success( function (data) {
            //Validating the data for its existence.
            if(data.hits.length>0){
                for (var i = 0; i < data.hits.length; i++) {
                    //Building the speech from the provided search term.
                    var searchphrase = "Please find the details below for the term " + $scope.recipe.toString();
                    var api = "https://stream.watsonplatform.net/text-to-speech/api/v1/synthesize?username=f1d9032a-38d0-4fa3-b467-3f05b054b297&password=iX1CYJcyfTtL&text=";
                    var audio = new Audio(api+searchphrase);
                    audio.play();
                    //Assigning the fetched data respectively
                    $scope.recipelist[i]= {
                        "name": data.hits[0].fields.item_name,
                        "nf_calories": data.hits[0].fields.nf_calories,
                        "nf_calories_from_fat": data.hits[0].fields.nf_calories_from_fat,
                        "nf_serving_weight_grams": data.hits[0].fields.nf_serving_weight_grams
                    };
                }
            }
            else
            {
                //On Failure
                //If data doesn't exist for the provided search term.
                $scope.recipelist[0]= {
                    "name": "Sorry! No Details found for the executed search. Please try again!",
                    "nf_calories": "No Details found!",
                    "nf_calories_from_fat": "No Details found!",
                    "nf_serving_weight_grams": "No Details found!"
                };
                //Building the speech for the non existent data.
                var searchphrase = "Sorry! No Details found for the executed search. Please try again!";
                var api = "https://stream.watsonplatform.net/text-to-speech/api/v1/synthesize?username=f1d9032a-38d0-4fa3-b467-3f05b054b297&password=iX1CYJcyfTtL&text=";
                var audio = new Audio(api+searchphrase);
                audio.play();
            }

        } )
    };

    $rootScope.updateSession = function () {
        //reads the session variables if exist from php
        $rootScope.session = "hello";

    };

    $rootScope.updateSession();


    // button functions
    $scope.getLoginStatus = function () {
        Facebook.getLoginStatus();

    };

    $scope.login = function () {
        Facebook.login();
    };

    $scope.logout = function () {
        Facebook.logout();
        console.log("inside");
        $rootScope.facebook_id = "";
    };

    $scope.unsubscribe = function () {
        Facebook.unsubscribe();
    }

    $scope.getInfo = function () {
        FB.api( '/' + $rootScope.facebook_id, function (response) {
            console.log( 'Good to see you, ' + response.name + '.' + $rootScope.facebook_id );

        } );
        $rootScope.info = $rootScope.session;

    };


} );


