function danger(message) {
    $.notify({
            // options
            icon: 'glyphicon glyphicon-remove-sign',
            message: message,
            title: 'Error',
        },
        {
            type: "danger",
            allow_dismiss: true,
            newest_on_top: true,
            placement: {
                from: "top",
                align: "right"
            },
            delay: 3000,
            animate: {
                enter: 'animated fadeInDown',
                exit: 'animated fadeOutUp'
            },
            offset: {
                y: 60,
                x: 20
            },
            template: '<div data-notify="container" class="col-xs-11 col-sm-4 alert alert-{0}" role="alert">' +
            '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">x</button>' +
            '<div data-notify="icon" style="margin-right:5px"></div> ' +
            '<span data-notify="title">{1}</span> ' +
            '<div data-notify="message">{2}</div>' +
            '<div class="progress" data-notify="progressbar">' +
            '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
            '</div>' +
            '<a href="{3}" target="{4}" data-notify="url"></a>' +
            '</div>'
        })
}

function success(message) {
    $.notify({
            // options
            icon: 'glyphicon glyphicon-ok-sign',
            message: message,
            title: 'Success',
        },
        {
            type: "success",
            allow_dismiss: true,
            newest_on_top: true,
            placement: {
                from: "top",
                align: "right"
            },
            delay: 3000,
            animate: {
                enter: 'animated fadeInDown',
                exit: 'animated fadeOutUp'
            },
            offset: {
                y: 60,
                x: 20
            },
            template: '<div data-notify="container" class="col-xs-11 col-sm-4 alert alert-{0}" role="alert">' +
            '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">x</button>' +
            '<div data-notify="icon" style="margin-right:5px"></div> ' +
            '<span data-notify="title">{1}</span> ' +
            '<div data-notify="message">{2}</div>' +
            '<div class="progress" data-notify="progressbar">' +
            '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
            '</div>' +
            '<a href="{3}" target="{4}" data-notify="url"></a>' +
            '</div>'
        });
}

function loadingShow() {
    $(".preloader").fadeIn();
    $("html").css('overflow', 'hidden');
}

function loadingHide() {
    $(".preloader").fadeOut();
    $("html").css('overflow', 'auto');
}

jQuery(document).ready(function () {
    $("body").append("<div id='divSmallBoxes'></div>"), $("body").append("<div id='divMiniIcons'></div><div id='divbigBoxes'></div>")
});
var ExistMsg = 0, SmartMSGboxCount = 0, PrevTop = 0;
$.SmartMessageBox = function (a, b) {
    var c, d;
    a = $.extend({
        "title": "",
        "content": "",
        "NormalButton": void 0,
        "ActiveButton": void 0,
        "buttons": void 0,
        "input": void 0,
        "inputValue": void 0,
        "placeholder": "",
        "options": void 0
    }, a);
    var e = 0;
    // if (e = 1, 0 == isIE8orlower() && $.sound_on) {
    //     var f = document.createElement("audio");
    //     f.setAttribute("src", $.sound_path + "messagebox.mp3"), f.addEventListener("load", function () {
    //         f.play()
    //     }, !0), f.pause(), f.play()
    // }
    SmartMSGboxCount += 1, 0 == ExistMsg && (ExistMsg = 1, c = "<div class='divMessageBox animated fadeIn fast' id='MsgBoxBack'></div>", $("body").append(c),$("#MsgBoxBack").addClass("MessageIE"));
    var g = "", h = 0;
    if (void 0 != a.input) switch (h = 1, a.input = a.input.toLowerCase(), a.input) {
        case"text":
            a.inputValue = "string" === $.type(a.inputValue) ? a.inputValue.replace(/'/g, "&#x27;") : a.inputValue, g = "<input class='form-control' type='" + a.input + "' id='txt" + SmartMSGboxCount + "' placeholder='" + a.placeholder + "' value='" + a.inputValue + "'/><br/><br/>";
            break;
        case"password":
            g = "<input class='form-control' type='" + a.input + "' id='txt" + SmartMSGboxCount + "' placeholder='" + a.placeholder + "'/><br/><br/>";
            break;
        case"select":
            if (void 0 == a.options) alert("For this type of input, the options parameter is required."); else {
                g = "<select class='form-control' id='txt" + SmartMSGboxCount + "'>";
                for (var i = 0; i <= a.options.length - 1; i++) "[" == a.options[i] ? j = "" : "]" == a.options[i] ? (k += 1, j = "<option>" + j + "</option>", g += j) : j += a.options[i];
                g += "</select>"
            }
            break;
        default:
            alert("That type of input is not handled yet")
    }
    d = "<div class='MessageBoxContainer animated fadeIn fast' id='Msg" + SmartMSGboxCount + "'>", d += "<div class='MessageBoxMiddle'>", d += "<span class='MsgTitle'>" + a.title + "</span class='MsgTitle'>", d += "<p class='pText'>" + a.content + "</p>", d += g, d += "<div class='MessageBoxButtonSection'>", void 0 == a.buttons && (a.buttons = "[Accept]"), a.buttons = $.trim(a.buttons), a.buttons = a.buttons.split("");
    var j = "", k = 0;
    void 0 == a.NormalButton && (a.NormalButton = "#232323"), void 0 == a.ActiveButton && (a.ActiveButton = "#ed145b");
    for (var i = 0; i <= a.buttons.length - 1; i++) "[" == a.buttons[i] ? j = "" : "]" == a.buttons[i] ? (k += 1, j = "<button id='bot" + k + "-Msg" + SmartMSGboxCount + "' class='btn btn-default btn-sm botTempo'> " + j + "</button>", d += j) : j += a.buttons[i];
    d += "</div>", d += "</div>", d += "</div>", SmartMSGboxCount > 1 && ($(".MessageBoxContainer").hide(), $(".MessageBoxContainer").css("z-index", 99999)), $(".divMessageBox").append(d), 1 == h && $("#txt" + SmartMSGboxCount).focus(), $(".botTempo").hover(function () {
        $(this).attr("id")
    }, function () {
        $(this).attr("id")
    }), $(".botTempo").click(function () {
        var a = $(this).attr("id"), c = a.substr(a.indexOf("-") + 1), d = $.trim($(this).text());
        if (1 == h) {
            if ("function" == typeof b) {
                var e = c.replace("Msg", ""), f = $("#txt" + e).val();
                b && b(d, f)
            }
        } else "function" == typeof b && b && b(d);
        $("#" + c).addClass("animated fadeOut fast"), SmartMSGboxCount -= 1, 0 == SmartMSGboxCount && $("#MsgBoxBack").removeClass("fadeIn").addClass("fadeOut").queue(function () {
            ExistMsg = 0, $(this).remove()
        })
    })
};
