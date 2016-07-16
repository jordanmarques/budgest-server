angular.module('budGestApp').directive('datepicker', function () {
        return {
            restrict: 'A',
            replace: false,
            scope: {
                format: '@'
            },
            template: '<input>',
            link: function ($scope, element, attrs) {
                var dp = $(element).datepicker({
                    autoclose: true,
                    format: $scope.format,
                    language: "fr",
                    todayBtn: "linked",
                    clearBtn: true,
                    forceParse: false,
                    orientation: "top auto"
                }).on('hide', function (e) {
                    var date = $(element).datepicker('getDate');
                    if (date == 'Invalid Date') {
                        $(element).datepicker('setDate', new Date());
                        $(element).datepicker('clearDates');
                    }
                });

            }
        };

    })
    .directive('dateFormatter', function () {
        return {
            require: 'ngModel',
            link: function (scope, element, attrs, ngModelController) {
                ngModelController.$parsers.push(function (data) {
                    var m = moment(data, 'DD/MM/YYYY',true);
                    if(m.isValid()) {
                        return m.format('x'); //convert to timestamp
                    } else {
                        return data;
                    }
                });
                ngModelController.$formatters.unshift(function (data) {
                    if (!data)
                        return data;
                    var formattedDate = moment(data, 'x', true).format('DD/MM/YYYY');
                    return formattedDate;
                });
            }
        };
    });