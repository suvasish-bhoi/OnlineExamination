/*

 * Author - Harshen Amarnath Pandey
 * Version - 1.0.5
 * Release - 09th June 2014
 * Copyright (c) 2014 - 2018 Harshen Pandey
*/

(function( $ ) {

    $.fn.countdowntimer = function( options ) {
        return this.each( function() {
            countdown( $(this), options );
        });
    };

    //Definition of private function countdown.
    function countdown( $this , options ) {
        var opts = $.extend( {}, $.fn.countdowntimer.defaults, options );
        var $this = $this;
        $this.addClass("style");
        var size = "";
        var borderColor = "";
        var fontColor = "";
        var backgroundColor = "";
        size = opts.size;
        borderColor = opts.borderColor;
        fontColor = opts.fontColor;
        backgroundColor = opts.backgroundColor;

        if(options.borderColor != undefined || options.fontColor != undefined || options.backgroundColor != undefined) {
            var customStyle = {
                "background": backgroundColor,
                "color" : fontColor,
                "border-color" : borderColor
            }
            $this.css(customStyle);
        } else {
            $this.addClass("colorDefinition");
        }

        if(options.size != undefined) {
            switch(size) {
                case "xl" :
                    $this.addClass("size_xl");
                    break;
                case "lg" :
                    $this.addClass("size_lg");
                    break;
                case "md" :
                    $this.addClass("size_md");
                    break;
                case "sm" :
                    $this.addClass("size_sm");
                    break;
                case "xs" :
                    $this.addClass("size_xs");
                    break;
            }
        } else if(size == "sm") {
            $this.addClass("size_sm");
        }

        if(options.startDate == undefined && options.dateAndTime == undefined && options.currentTime == undefined && (options.hours != undefined || options.minutes != undefined || options.seconds != undefined)) {

            if(options.hours != undefined && options.minutes == undefined && options.seconds == undefined) {
                hours_H = "";
                minutes_H = "";
                seconds_H = "";
                timer_H = "";
                window['hours_H'+ $this.attr('id')] = opts.hours;
                window['minutes_H'+ $this.attr('id')] = opts.minutes;
                window['seconds_H'+ $this.attr('id')] = opts.seconds;
                onlyHours($this, opts);
                window['timer_H'+ $this.attr('id')] = setInterval(function(){
                    onlyHours($this, opts)
                }, opts.tickInterval * 1000);
            } else if(options.hours == undefined && options.minutes != undefined && options.seconds == undefined) {
                hours_M = "";
                minutes_M = "";
                seconds_M = "";
                timer_M = "";
                window['hours_M'+ $this.attr('id')] = opts.hours;
                window['minutes_M'+ $this.attr('id')] = opts.minutes;
                window['seconds_M'+ $this.attr('id')] = opts.seconds;
                onlyMinutes($this, opts);
                window['timer_M'+ $this.attr('id')] = setInterval(function(){
                    onlyMinutes($this, opts)
                }, opts.tickInterval * 1000);
            } else if(options.hours == undefined && options.minutes == undefined && options.seconds != undefined) {
                hours_S = "";
                minutes_S = "";
                seconds_S = "";
                timer_S = "";
                window['hours_S'+ $this.attr('id')] = opts.hours;
                window['minutes_S'+ $this.attr('id')] = opts.minutes;
                window['seconds_S'+ $this.attr('id')] = opts.seconds;
                onlySeconds($this, opts);
                window['timer_S'+ $this.attr('id')] = setInterval(function(){
                    onlySeconds($this, opts)
                }, opts.tickInterval * 1000);
            } else if(options.hours != undefined && options.minutes != undefined && options.seconds == undefined) {
                hours_HM = "";
                minutes_HM = "";
                seconds_HM = "";
                timer_HM = "";
                window['hours_HM'+ $this.attr('id')] = opts.hours;
                window['minutes_HM'+ $this.attr('id')] = opts.minutes;
                window['seconds_HM'+ $this.attr('id')] = opts.seconds;
                hoursMinutes($this, opts);
                window['timer_HM'+ $this.attr('id')] = setInterval(function(){
                    hoursMinutes($this, opts)
                }, opts.tickInterval * 1000);
            } else if(options.hours == undefined && options.minutes != undefined && options.seconds != undefined) {
                hours_MS = "";
                minutes_MS = "";
                seconds_MS = "";
                timer_MS = "";
                window['hours_MS'+ $this.attr('id')] = opts.hours;
                window['minutes_MS'+ $this.attr('id')] = opts.minutes;
                window['seconds_MS'+ $this.attr('id')] = opts.seconds;
                minutesSeconds($this, opts);
                window['timer_MS'+ $this.attr('id')] = setInterval(function(){
                    minutesSeconds($this, opts)
                }, opts.tickInterval * 1000);
            } else if(options.hours != undefined && options.minutes == undefined && options.seconds != undefined) {
                hours_HS = "";
                minutes_HS = "";
                seconds_HS = "";
                timer_HS = "";
                window['hours_HS'+ $this.attr('id')] = opts.hours;
                window['minutes_HS'+ $this.attr('id')] = opts.minutes;
                window['seconds_HS'+ $this.attr('id')] = opts.seconds;
                hoursSeconds($this, opts);
                window['timer_HS'+ $this.attr('id')] = setInterval(function(){
                    hoursSeconds($this, opts)
                }, opts.tickInterval * 1000);
            } else if(options.hours != undefined && options.minutes != undefined && options.seconds != undefined) {
                hours_HMS = "";
                minutes_HMS = "";
                seconds_HMS = "";
                timer_HMS = "";
                window['hours_HMS'+ $this.attr('id')] = opts.hours;
                window['minutes_HMS'+ $this.attr('id')] = opts.minutes;
                window['seconds_HMS'+ $this.attr('id')] = opts.seconds;
                hoursMinutesSeconds($this, opts);
                window['timer_HMS'+ $this.attr('id')] = setInterval(function(){
                    hoursMinutesSeconds($this, opts)
                }, opts.tickInterval * 1000);
            }

        } else if(options.startDate != undefined && options.dateAndTime != undefined && options.currentTime == undefined) {
            startDate = "";
            endDate = "";
            timer_startDate = "";
            window['startDate'+ $this.attr('id')] = new Date(opts.startDate);
            window['endDate'+ $this.attr('id')] = new Date(opts.dateAndTime);
            var type = "withStart";
            givenDate($this, opts, type);
            window['timer_startDate'+$this.attr('id')] = setInterval(function(){
                givenDate($this, opts, type)
            }, opts.tickInterval * 1000);
        } else if(options.startDate == undefined && options.dateAndTime != undefined && options.currentTime == undefined) {
            startTime = "";
            dateTime = "";
            timer_givenDate = "";
            var hour = opts.startDate.getHours() < 10?'0'+opts.startDate.getHours():opts.startDate.getHours();
            var minutes = opts.startDate.getMinutes() < 10?'0'+opts.startDate.getMinutes():opts.startDate.getMinutes();
            var seconds = opts.startDate.getSeconds() < 10?'0'+opts.startDate.getSeconds():opts.startDate.getSeconds();
            var month = (opts.startDate.getMonth()+1) < 10?'0'+(opts.startDate.getMonth()+1):(opts.startDate.getMonth()+1);
            var date = opts.startDate.getDate() < 10?'0'+opts.startDate.getDate():opts.startDate.getDate();
            var year = opts.startDate.getFullYear();
            window['startTime'+ $this.attr('id')] = new Date(year+'/'+month+'/'+date+' '+hour+':'+minutes+':'+seconds);
            window['dateTime'+ $this.attr('id')] = new Date(opts.dateAndTime);
            var type = "withnoStart";
            givenDate($this, opts, type);
            window['timer_givenDate'+$this.attr('id')] = setInterval(function(){
                givenDate($this, opts, type)
            }, opts.tickInterval * 1000);
        } else if(options.currentTime != undefined) {
            currentTime = "";
            timer_currentDate = "";
            window['currentTime' + $this.attr('id')] = opts.currentTime;
            currentDate($this, opts);
            window['timer_currentDate' + $this.attr('id')] = setInterval(function(){
                currentDate($this, opts)
            }, opts.tickInterval * 1000);
        } else {
            countSeconds = "";
            timer_secondsTimer = "";
            window['countSeconds'+ $this.attr('id')] = opts.seconds;
            window['timer_secondsTimer'+ $this.attr('id')] = setInterval(function(){
                secondsTimer($this)
            },1000);
        }
    };

    //Function for hours, minutes and seconds are set when invoking plugin.
    function hoursMinutesSeconds($this, opts) {
    	clockUpdate();
        var id = $this.attr('id');
        if(window['minutes_HMS'+ id] == opts.minutes && window['seconds_HMS'+ id] == opts.seconds && window['hours_HMS'+ id] == opts.hours) {
            if(window['hours_HMS'+ id].toString().length < 2) {
                window['hours_HMS'+ id] = "0" + window['hours_HMS'+ id];
            }
            if(window['minutes_HMS'+ id].toString().length < 2) {
                window['minutes_HMS'+ id] = "0" + window['minutes_HMS'+ id];
            }
            if(window['seconds_HMS'+ id].toString().length < 2) {
                window['seconds_HMS'+ id] = "0" + window['seconds_HMS'+ id];
            }
            $this.html(window['hours_HMS'+ id]+opts.timeSeparator+window['minutes_HMS'+ id]+opts.timeSeparator+window['seconds_HMS'+ id]);
            if(window['hours_HMS'+ id] == 0 && window['minutes_HMS'+ id] == 0 && window['seconds_HMS'+ id] == 0) {
                delete window['hours_HMS'+ id];
                delete window['minutes_HMS'+ id];
                delete window['seconds_HMS'+ id];
                clearInterval(window['timer_HMS'+ id]);
                timeUp($this, opts);
            } else if(window['hours_HMS'+ id] != 0 && window['minutes_HMS'+ id] == 0 && window['seconds_HMS'+ id] == 0) {
                window['hours_HMS'+ id]--;
                window['minutes_HMS'+ id] = 59;
                window['seconds_HMS'+ id] = 60 - opts.tickInterval;
            } else if(window['hours_HMS'+ id] == 0 && window['minutes_HMS'+ id] != 0 && window['seconds_HMS'+ id] == 0) {
                window['minutes_HMS'+ id]--;
                window['seconds_HMS'+ id] = 60 - opts.tickInterval;
            } else if(window['hours_HMS'+ id] != 0 && window['minutes_HMS'+ id] != 0 && window['seconds_HMS'+ id] == 0) {
                window['minutes_HMS'+ id]--;
                window['seconds_HMS'+ id] = 60 - opts.tickInterval;
            } else {
                window['seconds_HMS'+ id] -= opts.tickInterval;
                
            }
        } else {
            if(window['hours_HMS'+ id].toString().length < 2) {
                window['hours_HMS'+ id] = "0" + window['hours_HMS'+ id];
            }
            if(window['minutes_HMS'+ id].toString().length < 2) {
                window['minutes_HMS'+ id] = "0" + window['minutes_HMS'+ id];
            }
            if(window['seconds_HMS'+ id].toString().length < 2) {
                window['seconds_HMS'+ id] = "0" + window['seconds_HMS'+ id];
            }
            $this.html(window['hours_HMS'+ id]+opts.timeSeparator+window['minutes_HMS'+ id]+opts.timeSeparator+window['seconds_HMS'+ id]);
            window['seconds_HMS'+ id] -= opts.tickInterval;
            if (window['minutes_HMS'+ id]!=0 && window['seconds_HMS'+ id] < 0){
                window['minutes_HMS'+ id]--;
                window['seconds_HMS'+ id] = 60 - opts.tickInterval;
            }
            if(window['minutes_HMS'+ id]==0 && window['seconds_HMS'+ id] < 0 && window['hours_HMS'+ id] != 0)
            {
                window['hours_HMS'+ id]--;
                window['minutes_HMS'+ id] = 59;
                window['seconds_HMS'+ id] = 60 - opts.tickInterval;
            }
            if(window['minutes_HMS'+ id]==0 && window['seconds_HMS'+ id] < 0 && window['hours_HMS'+ id] == 0)
            {
                delete window['hours_HMS'+ id];
                delete window['minutes_HMS'+ id];
                delete window['seconds_HMS'+ id];
                clearInterval(window['timer_HMS'+ id]);
                timeUp($this, opts);
            }
        }
        id = null;
    }

    //Default function called when no options are set.
    function secondsTimer($this) {
        var id = $this.attr('id');
        if(window['countSeconds'+ id].toString().length < 2) {
            window['countSeconds'+ id] = "0" + window['countSeconds'+ id];
        }
        $this.html(window['countSeconds'+ id]+" "+"sec");
        window['countSeconds'+ id]--;
        if(window['countSeconds'+ id]==-1)
        {
            delete window['countSeconds'+ id];
            clearInterval(window['timer_secondsTimer'+ id]);
        }
        id = null;
    }

    //Function for calling the given function name when time is expired.
    function timeUp($this, opts) {
    	alert("Exam Time Off");
    	sendAjaxForEndTime();
        if(opts.timeUp != null) {
            if($.isFunction(opts.timeUp) == true) {
				
                opts.timeUp.apply($this, []);
            }
        }
    }

    //Giving default value for options.
    $.fn.countdowntimer.defaults = {
        hours   : 0,
        minutes : 0,
        seconds : 60,
        startDate : new Date(),
        dateAndTime : new Date("0000/00/00 00:00:00"),
        currentTime : false,
        size : "sm",
        borderColor : "#F0068E",
        fontColor : "#FFFFFF",
        backgroundColor : "#000000",
        timeSeparator : ":",
        tickInterval : 1,
        timeUp : null
    };

}(jQuery));
