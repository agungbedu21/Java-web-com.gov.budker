<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MODAL ADD -->
<style>
    .boxIconInside {
        display: contents;
    }
</style>
<div data-backdrop="static" class="modal fade in" id="modalMenu" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    x
                </button>
                <h4 style="font-weight: bold;color: teal;" class="modal-title" id="myModalLabel">${viewFor} Menu
                    Baru</h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label><b>Kode Menu</b></label>
                            <input id="menuId" name="menuId" type="hidden" class="freeText form-control" placeholder=""
                                   required="required" value="${data.menuId}">
                            <input id="menuCode" name="menuCode" type="text" class="freeText form-control"
                                   placeholder="" required="required" value="${data.menuCode}">
                        </div>
                        <div class="form-group">
                            <label><b>Nama Menu</b></label>
                            <input id="menuName" name="menuName" type="text" class="freeText form-control"
                                   placeholder="" required="required" value="${data.menuName}">
                        </div>
                        <div class="form-group">
                            <label><b>Menu Controller</b></label>
                            <input id="menuController" name="menuController" type="text" class="freeText form-control"
                                   placeholder="" required="required" value="${data.menuUrl}">
                        </div>
                        <div class="form-group">
                            <label><b>Deskripsi</b></label>
                            <input id="menuDescription" name="menuDescription" type="text" class="freeText form-control"
                                   placeholder="" required="required" value="${data.menuDescription}">
                        </div>
                        <div class="form-group">
                            <label><b>Ikon Menu</b></label>
                            <input id="menuIcon" type="text" class="freeText form-control" placeholder=""
                                   value="${data.menuIcon}">
                            <div style="display: none;" class="boxIcon">
                                <div data-icon="fa fa-automobile" class="boxIconInside"><i
                                        class="fa-4x fa fa-automobile"></i></div>
                                <div data-icon="fa fa-bank" class="boxIconInside"><i class="fa-4x fa fa-bank"></i></div>
                                <div data-icon="fa fa-behance" class="boxIconInside"><i class="fa-4x fa fa-behance"></i>
                                </div>
                                <div data-icon="fa fa-behance-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-behance-square"></i></div>
                                <div data-icon="fa fa-bomb" class="boxIconInside"><i class="fa-4x fa fa-bomb"></i></div>
                                <div data-icon="fa fa-building" class="boxIconInside"><i
                                        class="fa-4x fa fa-building"></i></div>
                                <div data-icon="fa fa-cab" class="boxIconInside"><i class="fa-4x fa fa-cab"></i></div>
                                <div data-icon="fa fa-car" class="boxIconInside"><i class="fa-4x fa fa-car"></i></div>
                                <div data-icon="fa fa-child" class="boxIconInside"><i class="fa-4x fa fa-child"></i>
                                </div>
                                <div data-icon="fa fa-circle-o-notch" class="boxIconInside"><i
                                        class="fa-4x fa fa-circle-o-notch"></i></div>
                                <div data-icon="fa fa-circle-thin" class="boxIconInside"><i
                                        class="fa-4x fa fa-circle-thin"></i></div>
                                <div data-icon="fa fa-codepen" class="boxIconInside"><i class="fa-4x fa fa-codepen"></i>
                                </div>
                                <div data-icon="fa fa-cube" class="boxIconInside"><i class="fa-4x fa fa-cube"></i></div>
                                <div data-icon="fa fa-cubes" class="boxIconInside"><i class="fa-4x fa fa-cubes"></i>
                                </div>
                                <div data-icon="fa fa-database" class="boxIconInside"><i
                                        class="fa-4x fa fa-database"></i></div>
                                <div data-icon="fa fa-delicious" class="boxIconInside"><i
                                        class="fa-4x fa fa-delicious"></i></div>
                                <div data-icon="fa fa-deviantart" class="boxIconInside"><i
                                        class="fa-4x fa fa-deviantart"></i></div>
                                <div data-icon="fa fa-digg" class="boxIconInside"><i class="fa-4x fa fa-digg"></i></div>
                                <div data-icon="fa fa-drupal" class="boxIconInside"><i class="fa-4x fa fa-drupal"></i>
                                </div>
                                <div data-icon="fa fa-empire" class="boxIconInside"><i class="fa-4x fa fa-empire"></i>
                                </div>
                                <div data-icon="fa fa-envelope-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-envelope-square"></i></div>
                                <div data-icon="fa fa-fax" class="boxIconInside"><i class="fa-4x fa fa-fax"></i></div>
                                <div data-icon="fa fa-file-archive-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-archive-o"></i></div>
                                <div data-icon="fa fa-file-audio-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-audio-o"></i></div>
                                <div data-icon="fa fa-file-code-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-code-o"></i></div>
                                <div data-icon="fa fa-file-excel-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-excel-o"></i></div>
                                <div data-icon="fa fa-file-image-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-image-o"></i></div>
                                <div data-icon="fa fa-file-movie-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-movie-o"></i></div>
                                <div data-icon="fa fa-file-pdf-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-pdf-o"></i></div>
                                <div data-icon="fa fa-file-photo-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-photo-o"></i></div>
                                <div data-icon="fa fa-file-picture-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-picture-o"></i></div>
                                <div data-icon="fa fa-file-powerpoint-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-powerpoint-o"></i></div>
                                <div data-icon="fa fa-file-sound-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-sound-o"></i></div>
                                <div data-icon="fa fa-file-video-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-video-o"></i></div>
                                <div data-icon="fa fa-file-word-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-word-o"></i></div>
                                <div data-icon="fa fa-file-zip-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-zip-o"></i></div>
                                <div data-icon="fa fa-ge" class="boxIconInside"><i class="fa-4x fa fa-ge"></i></div>
                                <div data-icon="fa fa-git" class="boxIconInside"><i class="fa-4x fa fa-git"></i></div>
                                <div data-icon="fa fa-git-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-git-square"></i></div>
                                <div data-icon="fa fa-google" class="boxIconInside"><i class="fa-4x fa fa-google"></i>
                                </div>
                                <div data-icon="fa fa-graduation-cap" class="boxIconInside"><i
                                        class="fa-4x fa fa-graduation-cap"></i></div>
                                <div data-icon="fa fa-hacker-news" class="boxIconInside"><i
                                        class="fa-4x fa fa-hacker-news"></i></div>
                                <div data-icon="fa fa-header" class="boxIconInside"><i class="fa-4x fa fa-header"></i>
                                </div>
                                <div data-icon="fa fa-history" class="boxIconInside"><i class="fa-4x fa fa-history"></i>
                                </div>
                                <div data-icon="fa fa-institution" class="boxIconInside"><i
                                        class="fa-4x fa fa-institution"></i></div>
                                <div data-icon="fa fa-joomla" class="boxIconInside"><i class="fa-4x fa fa-joomla"></i>
                                </div>
                                <div data-icon="fa fa-jsfiddle" class="boxIconInside"><i
                                        class="fa-4x fa fa-jsfiddle"></i></div>
                                <div data-icon="fa fa-language" class="boxIconInside"><i
                                        class="fa-4x fa fa-language"></i></div>
                                <div data-icon="fa fa-life-bouy" class="boxIconInside"><i
                                        class="fa-4x fa fa-life-bouy"></i></div>
                                <div data-icon="fa fa-life-ring" class="boxIconInside"><i
                                        class="fa-4x fa fa-life-ring"></i></div>
                                <div data-icon="fa fa-life-saver" class="boxIconInside"><i
                                        class="fa-4x fa fa-life-saver"></i></div>
                                <div data-icon="fa fa-mortar-board" class="boxIconInside"><i
                                        class="fa-4x fa fa-mortar-board"></i></div>
                                <div data-icon="fa fa-openid" class="boxIconInside"><i class="fa-4x fa fa-openid"></i>
                                </div>
                                <div data-icon="fa fa-paper-plane" class="boxIconInside"><i
                                        class="fa-4x fa fa-paper-plane"></i></div>
                                <div data-icon="fa fa-paper-plane-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-paper-plane-o"></i></div>
                                <div data-icon="fa fa-paragraph" class="boxIconInside"><i
                                        class="fa-4x fa fa-paragraph"></i></div>
                                <div data-icon="fa fa-paw" class="boxIconInside"><i class="fa-4x fa fa-paw"></i></div>
                                <div data-icon="fa fa-pied-piper" class="boxIconInside"><i
                                        class="fa-4x fa fa-pied-piper"></i></div>
                                <div data-icon="fa fa-pied-piper-alt" class="boxIconInside"><i
                                        class="fa-4x fa fa-pied-piper-alt"></i></div>
                                <div data-icon="fa fa-pied-piper-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-pied-piper-square"></i></div>
                                <div data-icon="fa fa-qq" class="boxIconInside"><i class="fa-4x fa fa-qq"></i></div>
                                <div data-icon="fa fa-ra" class="boxIconInside"><i class="fa-4x fa fa-ra"></i></div>
                                <div data-icon="fa fa-rebel" class="boxIconInside"><i class="fa-4x fa fa-rebel"></i>
                                </div>
                                <div data-icon="fa fa-recycle" class="boxIconInside"><i class="fa-4x fa fa-recycle"></i>
                                </div>
                                <div data-icon="fa fa-reddit" class="boxIconInside"><i class="fa-4x fa fa-reddit"></i>
                                </div>
                                <div data-icon="fa fa-reddit-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-reddit-square"></i></div>
                                <div data-icon="fa fa-send" class="boxIconInside"><i class="fa-4x fa fa-send"></i></div>
                                <div data-icon="fa fa-send-o" class="boxIconInside"><i class="fa-4x fa fa-send-o"></i>
                                </div>
                                <div data-icon="fa fa-share-alt" class="boxIconInside"><i
                                        class="fa-4x fa fa-share-alt"></i></div>
                                <div data-icon="fa fa-share-alt-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-share-alt-square"></i></div>
                                <div data-icon="fa fa-slack" class="boxIconInside"><i class="fa-4x fa fa-slack"></i>
                                </div>
                                <div data-icon="fa fa-sliders" class="boxIconInside"><i class="fa-4x fa fa-sliders"></i>
                                </div>
                                <div data-icon="fa fa-soundcloud" class="boxIconInside"><i
                                        class="fa-4x fa fa-soundcloud"></i></div>
                                <div data-icon="fa fa-space-shuttle" class="boxIconInside"><i
                                        class="fa-4x fa fa-space-shuttle"></i></div>
                                <div data-icon="fa fa-spoon" class="boxIconInside"><i class="fa-4x fa fa-spoon"></i>
                                </div>
                                <div data-icon="fa fa-spotify" class="boxIconInside"><i class="fa-4x fa fa-spotify"></i>
                                </div>
                                <div data-icon="fa fa-steam" class="boxIconInside"><i class="fa-4x fa fa-steam"></i>
                                </div>
                                <div data-icon="fa fa-steam-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-steam-square"></i></div>
                                <div data-icon="fa fa-stumbleupon" class="boxIconInside"><i
                                        class="fa-4x fa fa-stumbleupon"></i></div>
                                <div data-icon="fa fa-stumbleupon-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-stumbleupon-circle"></i></div>
                                <div data-icon="fa fa-support" class="boxIconInside"><i class="fa-4x fa fa-support"></i>
                                </div>
                                <div data-icon="fa fa-taxi" class="boxIconInside"><i class="fa-4x fa fa-taxi"></i></div>
                                <div data-icon="fa fa-tencent-weibo" class="boxIconInside"><i
                                        class="fa-4x fa fa-tencent-weibo"></i></div>
                                <div data-icon="fa fa-tree" class="boxIconInside"><i class="fa-4x fa fa-tree"></i></div>
                                <div data-icon="fa fa-university" class="boxIconInside"><i
                                        class="fa-4x fa fa-university"></i></div>
                                <div data-icon="fa fa-vine" class="boxIconInside"><i class="fa-4x fa fa-vine"></i></div>
                                <div data-icon="fa fa-wechat" class="boxIconInside"><i class="fa-4x fa fa-wechat"></i>
                                </div>
                                <div data-icon="fa fa-weixin" class="boxIconInside"><i class="fa-4x fa fa-weixin"></i>
                                </div>
                                <div data-icon="fa fa-wordpress" class="boxIconInside"><i
                                        class="fa-4x fa fa-wordpress"></i></div>
                                <div data-icon="fa fa-yahoo" class="boxIconInside"><i class="fa-4x fa fa-yahoo"></i>
                                </div>
                                <div data-icon="fa fa-adjust" class="boxIconInside"><i class="fa-4x fa fa-adjust"></i>
                                </div>
                                <div data-icon="fa fa-anchor" class="boxIconInside"><i class="fa-4x fa fa-anchor"></i>
                                </div>
                                <div data-icon="fa fa-archive" class="boxIconInside"><i class="fa-4x fa fa-archive"></i>
                                </div>
                                <div data-icon="fa fa-asterisk" class="boxIconInside"><i
                                        class="fa-4x fa fa-asterisk"></i></div>
                                <div data-icon="fa fa-ban" class="boxIconInside"><i class="fa-4x fa fa-ban"></i></div>
                                <div data-icon="fa fa-bar-chart-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-bar-chart-o"></i></div>
                                <div data-icon="fa fa-barcode" class="boxIconInside"><i class="fa-4x fa fa-barcode"></i>
                                </div>
                                <div data-icon="fa fa-beer" class="boxIconInside"><i class="fa-4x fa fa-beer"></i></div>
                                <div data-icon="fa fa-bell" class="boxIconInside"><i class="fa-4x fa fa-bell"></i></div>
                                <div data-icon="fa fa-bell-o" class="boxIconInside"><i class="fa-4x fa fa-bell-o"></i>
                                </div>
                                <div data-icon="fa fa-bolt" class="boxIconInside"><i class="fa-4x fa fa-bolt"></i></div>
                                <div data-icon="fa fa-book" class="boxIconInside"><i class="fa-4x fa fa-book"></i></div>
                                <div data-icon="fa fa-bookmark" class="boxIconInside"><i
                                        class="fa-4x fa fa-bookmark"></i></div>
                                <div data-icon="fa fa-bookmark-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-bookmark-o"></i></div>
                                <div data-icon="fa fa-briefcase" class="boxIconInside"><i
                                        class="fa-4x fa fa-briefcase"></i></div>
                                <div data-icon="fa fa-bug" class="boxIconInside"><i class="fa-4x fa fa-bug"></i></div>
                                <div data-icon="fa fa-building" class="boxIconInside"><i
                                        class="fa-4x fa fa-building"></i></div>
                                <div data-icon="fa fa-bullhorn" class="boxIconInside"><i
                                        class="fa-4x fa fa-bullhorn"></i></div>
                                <div data-icon="fa fa-bullseye" class="boxIconInside"><i
                                        class="fa-4x fa fa-bullseye"></i></div>
                                <div data-icon="fa fa-calendar" class="boxIconInside"><i
                                        class="fa-4x fa fa-calendar"></i></div>
                                <div data-icon="fa fa-calendar-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-calendar-o"></i></div>
                                <div data-icon="fa fa-camera" class="boxIconInside"><i class="fa-4x fa fa-camera"></i>
                                </div>
                                <div data-icon="fa fa-camera-retro" class="boxIconInside"><i
                                        class="fa-4x fa fa-camera-retro"></i></div>
                                <div data-icon="fa fa-caret-square-o-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-square-o-down"></i></div>
                                <div data-icon="fa fa-caret-square-o-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-square-o-left"></i></div>
                                <div data-icon="fa fa-caret-square-o-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-square-o-right"></i></div>
                                <div data-icon="fa fa-caret-square-o-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-square-o-up"></i></div>
                                <div data-icon="fa fa-certificate" class="boxIconInside"><i
                                        class="fa-4x fa fa-certificate"></i></div>
                                <div data-icon="fa fa-check" class="boxIconInside"><i class="fa-4x fa fa-check"></i>
                                </div>
                                <div data-icon="fa fa-check-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-check-circle"></i></div>
                                <div data-icon="fa fa-check-circle-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-check-circle-o"></i></div>
                                <div data-icon="fa fa-check-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-check-square"></i></div>
                                <div data-icon="fa fa-check-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-check-square-o"></i></div>
                                <div data-icon="fa fa-circle" class="boxIconInside"><i class="fa-4x fa fa-circle"></i>
                                </div>
                                <div data-icon="fa fa-circle-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-circle-o"></i></div>
                                <div data-icon="fa fa-clock-o" class="boxIconInside"><i class="fa-4x fa fa-clock-o"></i>
                                </div>
                                <div data-icon="fa fa-cloud" class="boxIconInside"><i class="fa-4x fa fa-cloud"></i>
                                </div>
                                <div data-icon="fa fa-cloud-download" class="boxIconInside"><i
                                        class="fa-4x fa fa-cloud-download"></i></div>
                                <div data-icon="fa fa-cloud-upload" class="boxIconInside"><i
                                        class="fa-4x fa fa-cloud-upload"></i></div>
                                <div data-icon="fa fa-code" class="boxIconInside"><i class="fa-4x fa fa-code"></i></div>
                                <div data-icon="fa fa-code-fork" class="boxIconInside"><i
                                        class="fa-4x fa fa-code-fork"></i></div>
                                <div data-icon="fa fa-coffee" class="boxIconInside"><i class="fa-4x fa fa-coffee"></i>
                                </div>
                                <div data-icon="fa fa-cog" class="boxIconInside"><i class="fa-4x fa fa-cog"></i></div>
                                <div data-icon="fa fa-cogs" class="boxIconInside"><i class="fa-4x fa fa-cogs"></i></div>
                                <div data-icon="fa fa-plus-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-plus-square-o"></i></div>
                                <div data-icon="fa fa-comment" class="boxIconInside"><i class="fa-4x fa fa-comment"></i>
                                </div>
                                <div data-icon="fa fa-comment-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-comment-o"></i></div>
                                <div data-icon="fa fa-comments" class="boxIconInside"><i
                                        class="fa-4x fa fa-comments"></i></div>
                                <div data-icon="fa fa-comments-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-comments-o"></i></div>
                                <div data-icon="fa fa-compass" class="boxIconInside"><i class="fa-4x fa fa-compass"></i>
                                </div>
                                <div data-icon="fa fa-credit-card" class="boxIconInside"><i
                                        class="fa-4x fa fa-credit-card"></i></div>
                                <div data-icon="fa fa-crop" class="boxIconInside"><i class="fa-4x fa fa-crop"></i></div>
                                <div data-icon="fa fa-crosshairs" class="boxIconInside"><i
                                        class="fa-4x fa fa-crosshairs"></i></div>
                                <div data-icon="fa fa-cutlery" class="boxIconInside"><i class="fa-4x fa fa-cutlery"></i>
                                </div>
                                <div data-icon="fa fa-dashboard" class="boxIconInside"><i
                                        class="fa-4x fa fa-dashboard"></i></div>
                                <div data-icon="fa fa-desktop" class="boxIconInside"><i class="fa-4x fa fa-desktop"></i>
                                </div>
                                <div data-icon="fa fa-dot-circle-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-dot-circle-o"></i></div>
                                <div data-icon="fa fa-download" class="boxIconInside"><i
                                        class="fa-4x fa fa-download"></i></div>
                                <div data-icon="fa fa-edit" class="boxIconInside"><i class="fa-4x fa fa-edit"></i></div>
                                <div data-icon="fa fa-ellipsis-horizontal" class="boxIconInside"><i
                                        class="fa-4x fa fa-ellipsis-horizontal"></i></div>
                                <div data-icon="fa fa-ellipsis-vertical" class="boxIconInside"><i
                                        class="fa-4x fa fa-ellipsis-vertical"></i></div>
                                <div data-icon="fa fa-envelope" class="boxIconInside"><i
                                        class="fa-4x fa fa-envelope"></i></div>
                                <div data-icon="fa fa-envelope-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-envelope-o"></i></div>
                                <div data-icon="fa fa-eraser" class="boxIconInside"><i class="fa-4x fa fa-eraser"></i>
                                </div>
                                <div data-icon="fa fa-exchange" class="boxIconInside"><i
                                        class="fa-4x fa fa-exchange"></i></div>
                                <div data-icon="fa fa-exclamation" class="boxIconInside"><i
                                        class="fa-4x fa fa-exclamation"></i></div>
                                <div data-icon="fa fa-exclamation-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-exclamation-circle"></i></div>
                                <div data-icon="fa fa-exclamation-triangle" class="boxIconInside"><i
                                        class="fa-4x fa fa-exclamation-triangle"></i></div>
                                <div data-icon="fa fa-minus-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-minus-square-o"></i></div>
                                <div data-icon="fa fa-external-link" class="boxIconInside"><i
                                        class="fa-4x fa fa-external-link"></i></div>
                                <div data-icon="fa fa-external-link-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-external-link-square"></i></div>
                                <div data-icon="fa fa-eye" class="boxIconInside"><i class="fa-4x fa fa-eye"></i></div>
                                <div data-icon="fa fa-eye-slash" class="boxIconInside"><i
                                        class="fa-4x fa fa-eye-slash"></i></div>
                                <div data-icon="fa fa-female" class="boxIconInside"><i class="fa-4x fa fa-female"></i>
                                </div>
                                <div data-icon="fa fa-fighter-jet" class="boxIconInside"><i
                                        class="fa-4x fa fa-fighter-jet"></i></div>
                                <div data-icon="fa fa-film" class="boxIconInside"><i class="fa-4x fa fa-film"></i></div>
                                <div data-icon="fa fa-filter" class="boxIconInside"><i class="fa-4x fa fa-filter"></i>
                                </div>
                                <div data-icon="fa fa-fire" class="boxIconInside"><i class="fa-4x fa fa-fire"></i></div>
                                <div data-icon="fa fa-fire-extinguisher" class="boxIconInside"><i
                                        class="fa-4x fa fa-fire-extinguisher"></i></div>
                                <div data-icon="fa fa-flag" class="boxIconInside"><i class="fa-4x fa fa-flag"></i></div>
                                <div data-icon="fa fa-flag-checkered" class="boxIconInside"><i
                                        class="fa-4x fa fa-flag-checkered"></i></div>
                                <div data-icon="fa fa-flag-o" class="boxIconInside"><i class="fa-4x fa fa-flag-o"></i>
                                </div>
                                <div data-icon="fa fa-flash" class="boxIconInside"><i class="fa-4x fa fa-flash"></i>
                                </div>
                                <div data-icon="fa fa-flask" class="boxIconInside"><i class="fa-4x fa fa-flask"></i>
                                </div>
                                <div data-icon="fa fa-folder" class="boxIconInside"><i class="fa-4x fa fa-folder"></i>
                                </div>
                                <div data-icon="fa fa-folder-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-folder-o"></i></div>
                                <div data-icon="fa fa-folder-open" class="boxIconInside"><i
                                        class="fa-4x fa fa-folder-open"></i></div>
                                <div data-icon="fa fa-folder-open-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-folder-open-o"></i></div>
                                <div data-icon="fa fa-frown-o" class="boxIconInside"><i class="fa-4x fa fa-frown-o"></i>
                                </div>
                                <div data-icon="fa fa-gamepad" class="boxIconInside"><i class="fa-4x fa fa-gamepad"></i>
                                </div>
                                <div data-icon="fa fa-gavel" class="boxIconInside"><i class="fa-4x fa fa-gavel"></i>
                                </div>
                                <div data-icon="fa fa-gear" class="boxIconInside"><i class="fa-4x fa fa-gear"></i></div>
                                <div data-icon="fa fa-gears" class="boxIconInside"><i class="fa-4x fa fa-gears"></i>
                                </div>
                                <div data-icon="fa fa-gift" class="boxIconInside"><i class="fa-4x fa fa-gift"></i></div>
                                <div data-icon="fa fa-glass" class="boxIconInside"><i class="fa-4x fa fa-glass"></i>
                                </div>
                                <div data-icon="fa fa-globe" class="boxIconInside"><i class="fa-4x fa fa-globe"></i>
                                </div>
                                <div data-icon="fa fa-group" class="boxIconInside"><i class="fa-4x fa fa-group"></i>
                                </div>
                                <div data-icon="fa fa-hdd-o" class="boxIconInside"><i class="fa-4x fa fa-hdd-o"></i>
                                </div>
                                <div data-icon="fa fa-headphones" class="boxIconInside"><i
                                        class="fa-4x fa fa-headphones"></i></div>
                                <div data-icon="fa fa-heart" class="boxIconInside"><i class="fa-4x fa fa-heart"></i>
                                </div>
                                <div data-icon="fa fa-heart-o" class="boxIconInside"><i class="fa-4x fa fa-heart-o"></i>
                                </div>
                                <div data-icon="fa fa-home" class="boxIconInside"><i class="fa-4x fa fa-home"></i></div>
                                <div data-icon="fa fa-inbox" class="boxIconInside"><i class="fa-4x fa fa-inbox"></i>
                                </div>
                                <div data-icon="fa fa-info" class="boxIconInside"><i class="fa-4x fa fa-info"></i></div>
                                <div data-icon="fa fa-info-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-info-circle"></i></div>
                                <div data-icon="fa fa-key" class="boxIconInside"><i class="fa-4x fa fa-key"></i></div>
                                <div data-icon="fa fa-keyboard-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-keyboard-o"></i></div>
                                <div data-icon="fa fa-laptop" class="boxIconInside"><i class="fa-4x fa fa-laptop"></i>
                                </div>
                                <div data-icon="fa fa-leaf" class="boxIconInside"><i class="fa-4x fa fa-leaf"></i></div>
                                <div data-icon="fa fa-legal" class="boxIconInside"><i class="fa-4x fa fa-legal"></i>
                                </div>
                                <div data-icon="fa fa-lemon-o" class="boxIconInside"><i class="fa-4x fa fa-lemon-o"></i>
                                </div>
                                <div data-icon="fa fa-level-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-level-down"></i></div>
                                <div data-icon="fa fa-level-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-level-up"></i></div>
                                <div data-icon="fa fa-lightbulb-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-lightbulb-o"></i></div>
                                <div data-icon="fa fa-location-arrow" class="boxIconInside"><i
                                        class="fa-4x fa fa-location-arrow"></i></div>
                                <div data-icon="fa fa-lock" class="boxIconInside"><i class="fa-4x fa fa-lock"></i></div>
                                <div data-icon="fa fa-magic" class="boxIconInside"><i class="fa-4x fa fa-magic"></i>
                                </div>
                                <div data-icon="fa fa-magnet" class="boxIconInside"><i class="fa-4x fa fa-magnet"></i>
                                </div>
                                <div data-icon="fa fa-mail-forward" class="boxIconInside"><i
                                        class="fa-4x fa fa-mail-forward"></i></div>
                                <div data-icon="fa fa-mail-reply" class="boxIconInside"><i
                                        class="fa-4x fa fa-mail-reply"></i></div>
                                <div data-icon="fa fa-mail-reply-all" class="boxIconInside"><i
                                        class="fa-4x fa fa-mail-reply-all"></i></div>
                                <div data-icon="fa fa-male" class="boxIconInside"><i class="fa-4x fa fa-male"></i></div>
                                <div data-icon="fa fa-map-marker" class="boxIconInside"><i
                                        class="fa-4x fa fa-map-marker"></i></div>
                                <div data-icon="fa fa-meh-o" class="boxIconInside"><i class="fa-4x fa fa-meh-o"></i>
                                </div>
                                <div data-icon="fa fa-microphone" class="boxIconInside"><i
                                        class="fa-4x fa fa-microphone"></i></div>
                                <div data-icon="fa fa-microphone-slash" class="boxIconInside"><i
                                        class="fa-4x fa fa-microphone-slash"></i></div>
                                <div data-icon="fa fa-minus" class="boxIconInside"><i class="fa-4x fa fa-minus"></i>
                                </div>
                                <div data-icon="fa fa-minus-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-minus-circle"></i></div>
                                <div data-icon="fa fa-minus-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-minus-square"></i></div>
                                <div data-icon="fa fa-minus-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-minus-square-o"></i></div>
                                <div data-icon="fa fa-mobile" class="boxIconInside"><i class="fa-4x fa fa-mobile"></i>
                                </div>
                                <div data-icon="fa fa-mobile-phone" class="boxIconInside"><i
                                        class="fa-4x fa fa-mobile-phone"></i></div>
                                <div data-icon="fa fa-money" class="boxIconInside"><i class="fa-4x fa fa-money"></i>
                                </div>
                                <div data-icon="fa fa-moon-o" class="boxIconInside"><i class="fa-4x fa fa-moon-o"></i>
                                </div>
                                <div data-icon="fa fa-move" class="boxIconInside"><i class="fa-4x fa fa-move"></i></div>
                                <div data-icon="fa fa-music" class="boxIconInside"><i class="fa-4x fa fa-music"></i>
                                </div>
                                <div data-icon="fa fa-pencil" class="boxIconInside"><i class="fa-4x fa fa-pencil"></i>
                                </div>
                                <div data-icon="fa fa-pencil-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-pencil-square"></i></div>
                                <div data-icon="fa fa-pencil-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-pencil-square-o"></i></div>
                                <div data-icon="fa fa-phone" class="boxIconInside"><i class="fa-4x fa fa-phone"></i>
                                </div>
                                <div data-icon="fa fa-phone-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-phone-square"></i></div>
                                <div data-icon="fa fa-picture-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-picture-o"></i></div>
                                <div data-icon="fa fa-plane" class="boxIconInside"><i class="fa-4x fa fa-plane"></i>
                                </div>
                                <div data-icon="fa fa-plus" class="boxIconInside"><i class="fa-4x fa fa-plus"></i></div>
                                <div data-icon="fa fa-plus-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-plus-circle"></i></div>
                                <div data-icon="fa fa-plus-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-plus-square"></i></div>
                                <div data-icon="fa fa-power-off" class="boxIconInside"><i
                                        class="fa-4x fa fa-power-off"></i></div>
                                <div data-icon="fa fa-print" class="boxIconInside"><i class="fa-4x fa fa-print"></i>
                                </div>
                                <div data-icon="fa fa-puzzle-piece" class="boxIconInside"><i
                                        class="fa-4x fa fa-puzzle-piece"></i></div>
                                <div data-icon="fa fa-qrcode" class="boxIconInside"><i class="fa-4x fa fa-qrcode"></i>
                                </div>
                                <div data-icon="fa fa-question" class="boxIconInside"><i
                                        class="fa-4x fa fa-question"></i></div>
                                <div data-icon="fa fa-question-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-question-circle"></i></div>
                                <div data-icon="fa fa-quote-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-quote-left"></i></div>
                                <div data-icon="fa fa-quote-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-quote-right"></i></div>
                                <div data-icon="fa fa-random" class="boxIconInside"><i class="fa-4x fa fa-random"></i>
                                </div>
                                <div data-icon="fa fa-refresh" class="boxIconInside"><i class="fa-4x fa fa-refresh"></i>
                                </div>
                                <div data-icon="fa fa-reorder" class="boxIconInside"><i class="fa-4x fa fa-reorder"></i>
                                </div>
                                <div data-icon="fa fa-reply" class="boxIconInside"><i class="fa-4x fa fa-reply"></i>
                                </div>
                                <div data-icon="fa fa-reply-all" class="boxIconInside"><i
                                        class="fa-4x fa fa-reply-all"></i></div>
                                <div data-icon="fa fa-resize-horizontal" class="boxIconInside"><i
                                        class="fa-4x fa fa-resize-horizontal"></i></div>
                                <div data-icon="fa fa-resize-vertical" class="boxIconInside"><i
                                        class="fa-4x fa fa-resize-vertical"></i></div>
                                <div data-icon="fa fa-retweet" class="boxIconInside"><i class="fa-4x fa fa-retweet"></i>
                                </div>
                                <div data-icon="fa fa-road" class="boxIconInside"><i class="fa-4x fa fa-road"></i></div>
                                <div data-icon="fa fa-rocket" class="boxIconInside"><i class="fa-4x fa fa-rocket"></i>
                                </div>
                                <div data-icon="fa fa-rss" class="boxIconInside"><i class="fa-4x fa fa-rss"></i></div>
                                <div data-icon="fa fa-rss-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-rss-square"></i></div>
                                <div data-icon="fa fa-search" class="boxIconInside"><i class="fa-4x fa fa-search"></i>
                                </div>
                                <div data-icon="fa fa-search-minus" class="boxIconInside"><i
                                        class="fa-4x fa fa-search-minus"></i></div>
                                <div data-icon="fa fa-search-plus" class="boxIconInside"><i
                                        class="fa-4x fa fa-search-plus"></i></div>
                                <div data-icon="fa fa-share" class="boxIconInside"><i class="fa-4x fa fa-share"></i>
                                </div>
                                <div data-icon="fa fa-share-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-share-square"></i></div>
                                <div data-icon="fa fa-share-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-share-square-o"></i></div>
                                <div data-icon="fa fa-shield" class="boxIconInside"><i class="fa-4x fa fa-shield"></i>
                                </div>
                                <div data-icon="fa fa-shopping-cart" class="boxIconInside"><i
                                        class="fa-4x fa fa-shopping-cart"></i></div>
                                <div data-icon="fa fa-sign-in" class="boxIconInside"><i class="fa-4x fa fa-sign-in"></i>
                                </div>
                                <div data-icon="fa fa-sign-out" class="boxIconInside"><i
                                        class="fa-4x fa fa-sign-out"></i></div>
                                <div data-icon="fa fa-signal" class="boxIconInside"><i class="fa-4x fa fa-signal"></i>
                                </div>
                                <div data-icon="fa fa-sitemap" class="boxIconInside"><i class="fa-4x fa fa-sitemap"></i>
                                </div>
                                <div data-icon="fa fa-smile-o" class="boxIconInside"><i class="fa-4x fa fa-smile-o"></i>
                                </div>
                                <div data-icon="fa fa-sort" class="boxIconInside"><i class="fa-4x fa fa-sort"></i></div>
                                <div data-icon="fa fa-sort-alpha-asc" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-alpha-asc"></i></div>
                                <div data-icon="fa fa-sort-alpha-desc" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-alpha-desc"></i></div>
                                <div data-icon="fa fa-sort-amount-asc" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-amount-asc"></i></div>
                                <div data-icon="fa fa-sort-amount-desc" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-amount-desc"></i></div>
                                <div data-icon="fa fa-sort-asc" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-asc"></i></div>
                                <div data-icon="fa fa-sort-desc" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-desc"></i></div>
                                <div data-icon="fa fa-sort-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-down"></i></div>
                                <div data-icon="fa fa-sort-numeric-asc" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-numeric-asc"></i></div>
                                <div data-icon="fa fa-sort-numeric-desc" class="boxIconInside"><i
                                        class="fa-4x fa fa-sort-numeric-desc"></i></div>
                                <div data-icon="fa fa-sort-up" class="boxIconInside"><i class="fa-4x fa fa-sort-up"></i>
                                </div>
                                <div data-icon="fa fa-spinner" class="boxIconInside"><i class="fa-4x fa fa-spinner"></i>
                                </div>
                                <div data-icon="fa fa-square" class="boxIconInside"><i class="fa-4x fa fa-square"></i>
                                </div>
                                <div data-icon="fa fa-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-square-o"></i></div>
                                <div data-icon="fa fa-star" class="boxIconInside"><i class="fa-4x fa fa-star"></i></div>
                                <div data-icon="fa fa-star-half" class="boxIconInside"><i
                                        class="fa-4x fa fa-star-half"></i></div>
                                <div data-icon="fa fa-star-half-empty" class="boxIconInside"><i
                                        class="fa-4x fa fa-star-half-empty"></i></div>
                                <div data-icon="fa fa-star-half-full" class="boxIconInside"><i
                                        class="fa-4x fa fa-star-half-full"></i></div>
                                <div data-icon="fa fa-star-half-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-star-half-o"></i></div>
                                <div data-icon="fa fa-star-o" class="boxIconInside"><i class="fa-4x fa fa-star-o"></i>
                                </div>
                                <div data-icon="fa fa-subscript" class="boxIconInside"><i
                                        class="fa-4x fa fa-subscript"></i></div>
                                <div data-icon="fa fa-suitcase" class="boxIconInside"><i
                                        class="fa-4x fa fa-suitcase"></i></div>
                                <div data-icon="fa fa-sun-o" class="boxIconInside"><i class="fa-4x fa fa-sun-o"></i>
                                </div>
                                <div data-icon="fa fa-superscript" class="boxIconInside"><i
                                        class="fa-4x fa fa-superscript"></i></div>
                                <div data-icon="fa fa-tablet" class="boxIconInside"><i class="fa-4x fa fa-tablet"></i>
                                </div>
                                <div data-icon="fa fa-tachometer" class="boxIconInside"><i
                                        class="fa-4x fa fa-tachometer"></i></div>
                                <div data-icon="fa fa-tag" class="boxIconInside"><i class="fa-4x fa fa-tag"></i></div>
                                <div data-icon="fa fa-tags" class="boxIconInside"><i class="fa-4x fa fa-tags"></i></div>
                                <div data-icon="fa fa-tasks" class="boxIconInside"><i class="fa-4x fa fa-tasks"></i>
                                </div>
                                <div data-icon="fa fa-terminal" class="boxIconInside"><i
                                        class="fa-4x fa fa-terminal"></i></div>
                                <div data-icon="fa fa-thumb-tack" class="boxIconInside"><i
                                        class="fa-4x fa fa-thumb-tack"></i></div>
                                <div data-icon="fa fa-thumbs-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-thumbs-down"></i></div>
                                <div data-icon="fa fa-thumbs-o-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-thumbs-o-down"></i></div>
                                <div data-icon="fa fa-thumbs-o-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-thumbs-o-up"></i></div>
                                <div data-icon="fa fa-thumbs-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-thumbs-up"></i></div>
                                <div data-icon="fa fa-ticket" class="boxIconInside"><i class="fa-4x fa fa-ticket"></i>
                                </div>
                                <div data-icon="fa fa-times" class="boxIconInside"><i class="fa-4x fa fa-times"></i>
                                </div>
                                <div data-icon="fa fa-times-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-times-circle"></i></div>
                                <div data-icon="fa fa-times-circle-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-times-circle-o"></i></div>
                                <div data-icon="fa fa-tint" class="boxIconInside"><i class="fa-4x fa fa-tint"></i></div>
                                <div data-icon="fa fa-toggle-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-toggle-down"></i></div>
                                <div data-icon="fa fa-toggle-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-toggle-left"></i></div>
                                <div data-icon="fa fa-toggle-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-toggle-right"></i></div>
                                <div data-icon="fa fa-toggle-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-toggle-up"></i></div>
                                <div data-icon="fa fa-trash-o" class="boxIconInside"><i class="fa-4x fa fa-trash-o"></i>
                                </div>
                                <div data-icon="fa fa-trophy" class="boxIconInside"><i class="fa-4x fa fa-trophy"></i>
                                </div>
                                <div data-icon="fa fa-truck" class="boxIconInside"><i class="fa-4x fa fa-truck"></i>
                                </div>
                                <div data-icon="fa fa-umbrella" class="boxIconInside"><i
                                        class="fa-4x fa fa-umbrella"></i></div>
                                <div data-icon="fa fa-unlock" class="boxIconInside"><i class="fa-4x fa fa-unlock"></i>
                                </div>
                                <div data-icon="fa fa-unlock-alt" class="boxIconInside"><i
                                        class="fa-4x fa fa-unlock-alt"></i></div>
                                <div data-icon="fa fa-unsorted" class="boxIconInside"><i
                                        class="fa-4x fa fa-unsorted"></i></div>
                                <div data-icon="fa fa-upload" class="boxIconInside"><i class="fa-4x fa fa-upload"></i>
                                </div>
                                <div data-icon="fa fa-user" class="boxIconInside"><i class="fa-4x fa fa-user"></i></div>
                                <div data-icon="fa fa-video-camera" class="boxIconInside"><i
                                        class="fa-4x fa fa-video-camera"></i></div>
                                <div data-icon="fa fa-volume-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-volume-down"></i></div>
                                <div data-icon="fa fa-volume-off" class="boxIconInside"><i
                                        class="fa-4x fa fa-volume-off"></i></div>
                                <div data-icon="fa fa-volume-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-volume-up"></i></div>
                                <div data-icon="fa fa-warning" class="boxIconInside"><i class="fa-4x fa fa-warning"></i>
                                </div>
                                <div data-icon="fa fa-wheelchair" class="boxIconInside"><i
                                        class="fa-4x fa fa-wheelchair"></i></div>
                                <div data-icon="fa fa-wrench" class="boxIconInside"><i class="fa-4x fa fa-wrench"></i>
                                </div>
                                <div data-icon="fa fa-check-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-check-square"></i></div>
                                <div data-icon="fa fa-check-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-check-square-o"></i></div>
                                <div data-icon="fa fa-circle" class="boxIconInside"><i class="fa-4x fa fa-circle"></i>
                                </div>
                                <div data-icon="fa fa-circle-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-circle-o"></i></div>
                                <div data-icon="fa fa-dot-circle-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-dot-circle-o"></i></div>
                                <div data-icon="fa fa-minus-square" class="boxIconInside"><i
                                        class="fa-4x fa fa-minus-square"></i></div>
                                <div data-icon="fa fa-minus-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-minus-square-o"></i></div>
                                <div data-icon="fa fa-square" class="boxIconInside"><i class="fa-4x fa fa-square"></i>
                                </div>
                                <div data-icon="fa fa-square-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-square-o"></i></div>
                                <div data-icon="fa fa-bitcoin" class="boxIconInside"><i class="fa-4x fa fa-bitcoin"></i>
                                </div>
                                <div data-icon="fa fa-btc" class="boxIconInside"><i class="fa-4x fa fa-btc"></i></div>
                                <div data-icon="fa fa-cny" class="boxIconInside"><i class="fa-4x fa fa-cny"></i></div>
                                <div data-icon="fa fa-dollar" class="boxIconInside"><i class="fa-4x fa fa-dollar"></i>
                                </div>
                                <div data-icon="fa fa-eur" class="boxIconInside"><i class="fa-4x fa fa-eur"></i></div>
                                <div data-icon="fa fa-euro" class="boxIconInside"><i class="fa-4x fa fa-euro"></i></div>
                                <div data-icon="fa fa-gbp" class="boxIconInside"><i class="fa-4x fa fa-gbp"></i></div>
                                <div data-icon="fa fa-inr" class="boxIconInside"><i class="fa-4x fa fa-inr"></i></div>
                                <div data-icon="fa fa-jpy" class="boxIconInside"><i class="fa-4x fa fa-jpy"></i></div>
                                <div data-icon="fa fa-krw" class="boxIconInside"><i class="fa-4x fa fa-krw"></i></div>
                                <div data-icon="fa fa-money" class="boxIconInside"><i class="fa-4x fa fa-money"></i>
                                </div>
                                <div data-icon="fa fa-rmb" class="boxIconInside"><i class="fa-4x fa fa-rmb"></i></div>
                                <div data-icon="fa fa-rouble" class="boxIconInside"><i class="fa-4x fa fa-rouble"></i>
                                </div>
                                <div data-icon="fa fa-rub" class="boxIconInside"><i class="fa-4x fa fa-rub"></i></div>
                                <div data-icon="fa fa-ruble" class="boxIconInside"><i class="fa-4x fa fa-ruble"></i>
                                </div>
                                <div data-icon="fa fa-rupee" class="boxIconInside"><i class="fa-4x fa fa-rupee"></i>
                                </div>
                                <div data-icon="fa fa-try" class="boxIconInside"><i class="fa-4x fa fa-try"></i></div>
                                <div data-icon="fa fa-turkish-lira" class="boxIconInside"><i
                                        class="fa-4x fa fa-turkish-lira"></i></div>
                                <div data-icon="fa fa-usd" class="boxIconInside"><i class="fa-4x fa fa-usd"></i></div>
                                <div data-icon="fa fa-won" class="boxIconInside"><i class="fa-4x fa fa-won"></i></div>
                                <div data-icon="fa fa-yen" class="boxIconInside"><i class="fa-4x fa fa-yen"></i></div>
                                <div data-icon="fa fa-align-center" class="boxIconInside"><i
                                        class="fa-4x fa fa-align-center"></i></div>
                                <div data-icon="fa fa-align-justify" class="boxIconInside"><i
                                        class="fa-4x fa fa-align-justify"></i></div>
                                <div data-icon="fa fa-align-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-align-left"></i></div>
                                <div data-icon="fa fa-align-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-align-right"></i></div>
                                <div data-icon="fa fa-bold" class="boxIconInside"><i class="fa-4x fa fa-bold"></i></div>
                                <div data-icon="fa fa-chain" class="boxIconInside"><i class="fa-4x fa fa-chain"></i>
                                </div>
                                <div data-icon="fa fa-chain-broken" class="boxIconInside"><i
                                        class="fa-4x fa fa-chain-broken"></i></div>
                                <div data-icon="fa fa-clipboard" class="boxIconInside"><i
                                        class="fa-4x fa fa-clipboard"></i></div>
                                <div data-icon="fa fa-columns" class="boxIconInside"><i class="fa-4x fa fa-columns"></i>
                                </div>
                                <div data-icon="fa fa-copy" class="boxIconInside"><i class="fa-4x fa fa-copy"></i></div>
                                <div data-icon="fa fa-cut" class="boxIconInside"><i class="fa-4x fa fa-cut"></i></div>
                                <div data-icon="fa fa-dedent" class="boxIconInside"><i class="fa-4x fa fa-dedent"></i>
                                </div>
                                <div data-icon="fa fa-eraser" class="boxIconInside"><i class="fa-4x fa fa-eraser"></i>
                                </div>
                                <div data-icon="fa fa-file" class="boxIconInside"><i class="fa-4x fa fa-file"></i></div>
                                <div data-icon="fa fa-file-o" class="boxIconInside"><i class="fa-4x fa fa-file-o"></i>
                                </div>
                                <div data-icon="fa fa-file-text" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-text"></i></div>
                                <div data-icon="fa fa-file-text-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-file-text-o"></i></div>
                                <div data-icon="fa fa-files-o" class="boxIconInside"><i class="fa-4x fa fa-files-o"></i>
                                </div>
                                <div data-icon="fa fa-floppy-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-floppy-o"></i></div>
                                <div data-icon="fa fa-font" class="boxIconInside"><i class="fa-4x fa fa-font"></i></div>
                                <div data-icon="fa fa-indent" class="boxIconInside"><i class="fa-4x fa fa-indent"></i>
                                </div>
                                <div data-icon="fa fa-italic" class="boxIconInside"><i class="fa-4x fa fa-italic"></i>
                                </div>
                                <div data-icon="fa fa-link" class="boxIconInside"><i class="fa-4x fa fa-link"></i></div>
                                <div data-icon="fa fa-list" class="boxIconInside"><i class="fa-4x fa fa-list"></i></div>
                                <div data-icon="fa fa-list-alt" class="boxIconInside"><i
                                        class="fa-4x fa fa-list-alt"></i></div>
                                <div data-icon="fa fa-list-ol" class="boxIconInside"><i class="fa-4x fa fa-list-ol"></i>
                                </div>
                                <div data-icon="fa fa-list-ul" class="boxIconInside"><i class="fa-4x fa fa-list-ul"></i>
                                </div>
                                <div data-icon="fa fa-outdent" class="boxIconInside"><i class="fa-4x fa fa-outdent"></i>
                                </div>
                                <div data-icon="fa fa-paperclip" class="boxIconInside"><i
                                        class="fa-4x fa fa-paperclip"></i></div>
                                <div data-icon="fa fa-paste" class="boxIconInside"><i class="fa-4x fa fa-paste"></i>
                                </div>
                                <div data-icon="fa fa-repeat" class="boxIconInside"><i class="fa-4x fa fa-repeat"></i>
                                </div>
                                <div data-icon="fa fa-rotate-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-rotate-left"></i></div>
                                <div data-icon="fa fa-rotate-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-rotate-right"></i></div>
                                <div data-icon="fa fa-save" class="boxIconInside"><i class="fa-4x fa fa-save"></i></div>
                                <div data-icon="fa fa-scissors" class="boxIconInside"><i
                                        class="fa-4x fa fa-scissors"></i></div>
                                <div data-icon="fa fa-strikethrough" class="boxIconInside"><i
                                        class="fa-4x fa fa-strikethrough"></i></div>
                                <div data-icon="fa fa-table" class="boxIconInside"><i class="fa-4x fa fa-table"></i>
                                </div>
                                <div data-icon="fa fa-text-height" class="boxIconInside"><i
                                        class="fa-4x fa fa-text-height"></i></div>
                                <div data-icon="fa fa-text-width" class="boxIconInside"><i
                                        class="fa-4x fa fa-text-width"></i></div>
                                <div data-icon="fa fa-th" class="boxIconInside"><i class="fa-4x fa fa-th"></i></div>
                                <div data-icon="fa fa-th-large" class="boxIconInside"><i
                                        class="fa-4x fa fa-th-large"></i></div>
                                <div data-icon="fa fa-th-list" class="boxIconInside"><i class="fa-4x fa fa-th-list"></i>
                                </div>
                                <div data-icon="fa fa-underline" class="boxIconInside"><i
                                        class="fa-4x fa fa-underline"></i></div>
                                <div data-icon="fa fa-undo" class="boxIconInside"><i class="fa-4x fa fa-undo"></i></div>
                                <div data-icon="fa fa-unlink" class="boxIconInside"><i class="fa-4x fa fa-unlink"></i>
                                </div>
                                <div data-icon="fa fa-angle-double-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-angle-double-down"></i></div>
                                <div data-icon="fa fa-angle-double-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-angle-double-left"></i></div>
                                <div data-icon="fa fa-angle-double-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-angle-double-right"></i></div>
                                <div data-icon="fa fa-angle-double-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-angle-double-up"></i></div>
                                <div data-icon="fa fa-angle-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-angle-down"></i></div>
                                <div data-icon="fa fa-angle-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-angle-left"></i></div>
                                <div data-icon="fa fa-angle-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-angle-right"></i></div>
                                <div data-icon="fa fa-angle-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-angle-up"></i></div>
                                <div data-icon="fa fa-arrow-circle-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-circle-down"></i></div>
                                <div data-icon="fa fa-arrow-circle-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-circle-left"></i></div>
                                <div data-icon="fa fa-arrow-circle-o-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-circle-o-down"></i></div>
                                <div data-icon="fa fa-arrow-circle-o-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-circle-o-left"></i></div>
                                <div data-icon="fa fa-arrow-circle-o-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-circle-o-right"></i></div>
                                <div data-icon="fa fa-arrow-circle-o-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-circle-o-up"></i></div>
                                <div data-icon="fa fa-arrow-circle-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-circle-right"></i></div>
                                <div data-icon="fa fa-arrow-circle-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-circle-up"></i></div>
                                <div data-icon="fa fa-arrow-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-down"></i></div>
                                <div data-icon="fa fa-arrow-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-left"></i></div>
                                <div data-icon="fa fa-arrow-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-right"></i></div>
                                <div data-icon="fa fa-arrow-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrow-up"></i></div>
                                <div data-icon="fa fa-caret-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-down"></i></div>
                                <div data-icon="fa fa-caret-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-left"></i></div>
                                <div data-icon="fa fa-caret-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-right"></i></div>
                                <div data-icon="fa fa-caret-square-o-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-square-o-down"></i></div>
                                <div data-icon="fa fa-caret-square-o-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-square-o-left"></i></div>
                                <div data-icon="fa fa-caret-square-o-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-square-o-right"></i></div>
                                <div data-icon="fa fa-caret-square-o-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-square-o-up"></i></div>
                                <div data-icon="fa fa-caret-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-caret-up"></i></div>
                                <div data-icon="fa fa-chevron-circle-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-chevron-circle-down"></i></div>
                                <div data-icon="fa fa-chevron-circle-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-chevron-circle-left"></i></div>
                                <div data-icon="fa fa-chevron-circle-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-chevron-circle-right"></i></div>
                                <div data-icon="fa fa-chevron-circle-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-chevron-circle-up"></i></div>
                                <div data-icon="fa fa-chevron-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-chevron-down"></i></div>
                                <div data-icon="fa fa-chevron-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-chevron-left"></i></div>
                                <div data-icon="fa fa-chevron-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-chevron-right"></i></div>
                                <div data-icon="fa fa-chevron-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-chevron-up"></i></div>
                                <div data-icon="fa fa-hand-o-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-hand-o-down"></i></div>
                                <div data-icon="fa fa-hand-o-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-hand-o-left"></i></div>
                                <div data-icon="fa fa-hand-o-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-hand-o-right"></i></div>
                                <div data-icon="fa fa-hand-o-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-hand-o-up"></i></div>
                                <div data-icon="fa fa-long-arrow-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-long-arrow-down"></i></div>
                                <div data-icon="fa fa-long-arrow-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-long-arrow-left"></i></div>
                                <div data-icon="fa fa-long-arrow-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-long-arrow-right"></i></div>
                                <div data-icon="fa fa-long-arrow-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-long-arrow-up"></i></div>
                                <div data-icon="fa fa-toggle-down" class="boxIconInside"><i
                                        class="fa-4x fa fa-toggle-down"></i></div>
                                <div data-icon="fa fa-toggle-left" class="boxIconInside"><i
                                        class="fa-4x fa fa-toggle-left"></i></div>
                                <div data-icon="fa fa-toggle-right" class="boxIconInside"><i
                                        class="fa-4x fa fa-toggle-right"></i></div>
                                <div data-icon="fa fa-toggle-up" class="boxIconInside"><i
                                        class="fa-4x fa fa-toggle-up"></i></div>
                                <div data-icon="fa fa-backward" class="boxIconInside"><i
                                        class="fa-4x fa fa-backward"></i></div>
                                <div data-icon="fa fa-eject" class="boxIconInside"><i class="fa-4x fa fa-eject"></i>
                                </div>
                                <div data-icon="fa fa-fast-backward" class="boxIconInside"><i
                                        class="fa-4x fa fa-fast-backward"></i></div>
                                <div data-icon="fa fa-fast-forward" class="boxIconInside"><i
                                        class="fa-4x fa fa-fast-forward"></i></div>
                                <div data-icon="fa fa-forward" class="boxIconInside"><i class="fa-4x fa fa-forward"></i>
                                </div>
                                <div data-icon="fa fa-arrows-alt" class="boxIconInside"><i
                                        class="fa-4x fa fa-arrows-alt"></i></div>
                                <div data-icon="fa fa-pause" class="boxIconInside"><i class="fa-4x fa fa-pause"></i>
                                </div>
                                <div data-icon="fa fa-play" class="boxIconInside"><i class="fa-4x fa fa-play"></i></div>
                                <div data-icon="fa fa-play-circle" class="boxIconInside"><i
                                        class="fa-4x fa fa-play-circle"></i></div>
                                <div data-icon="fa fa-play-circle-o" class="boxIconInside"><i
                                        class="fa-4x fa fa-play-circle-o"></i></div>
                                <div data-icon="fa fa-expand" class="boxIconInside"><i class="fa-4x fa fa-expand"></i>
                                </div>
                                <div data-icon="fa fa-compress" class="boxIconInside"><i
                                        class="fa-4x fa fa-compress"></i></div>
                                <div data-icon="fa fa-step-backward" class="boxIconInside"><i
                                        class="fa-4x fa fa-step-backward"></i></div>
                                <div data-icon="fa fa-step-forward" class="boxIconInside"><i
                                        class="fa-4x fa fa-step-forward"></i></div>
                                <div data-icon="fa fa-stop" class="boxIconInside"><i class="fa-4x fa fa-stop"></i></div>
                                <div data-icon="fa fa-youtube-play" class="boxIconInside"><i
                                        class="fa-4x fa fa-youtube-play"></i></div>

                            </div>
                        </div>
                        <div class="form-group">
                            <label><b>Menu Order</b></label>
                            <input id="menuOrder" type="number" class="onlyInteger form-control" placeholder=""
                                   value="${data.menuOrder}">
                        </div>
                        <div class="form-group">
                            <label for="parentId"><b>Menu Utama</b></label>

                            <select class="form-control select2" id="parentId">
                                <option value="">-- No Parent --</option>
                                <c:forEach items="${parentList}" var="listItem">
                                    <option value="${listItem.menuId}" <c:if
                                            test="${listItem.menuId == data.parentId}"> selected</c:if>
                                    >${listItem.menuCode} - ${listItem.menuName} - ${listItem.menuUrl}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label><b>Status</b></label>
                            <select id="isActive" class="form-control">
                                <option value="1">Aktif</option>
                                <option value="0">Tidak Aktif</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Batal
                </button>
                <button onclick="validate()" type="button" class="btn btn-primary">
                    Submit
                </button>
            </div>
        </div> <!--/.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- END MODAL ADD -->

<script type="text/javascript">
    $(document).ready(function () {
        $("input#menuIcon").click(function () {
            $(".boxIcon").show();
        });
        $(".boxIconInside").click(function () {
            $("input#menuIcon").val($(this).attr('data-icon'));
            $(".boxIcon").hide();

        });

        $('.select2').select2();
    });
    $('#modalMenu').bootstrapValidator({
        framework: 'bootstrap',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            menuCode: {
                validators: {
                    notEmpty: {
                        message: 'Field Ini Tidak Boleh Kosong'
                    }
                }
            },
            menuName: {
                validators: {
                    notEmpty: {
                        message: 'Field Ini Tidak Boleh Kosong'
                    }
                }
            },
            menuController: {
                validators: {
                    notEmpty: {
                        message: 'Field Ini Tidak Boleh Kosong'
                    }
                }
            },
            pageTitle: {
                validators: {
                    notEmpty: {
                        message: 'Field Ini Tidak Boleh Kosong'
                    }
                }
            }
        }
    })
        .off('success.form.bv')
        .on('success.form.bv', function (e) {
            submitDataAddMenu();

        });


    function submitDataAddMenu() {
        var idData = $("#menuId").val();
        var is = null;
        if (idData === "") {
            is = 1;
        } else {
            is = 2;
        }
        var data = {
            "menuId": $("#menuId").val(),
            "menuCode": $('input#menuCode').val(),
            "menuName": $('input#menuName').val(),
            "menuUrl": $('input#menuController').val(),
            "menuIcon": $('input#menuIcon').val(),
            "menuOrder": $('input#menuOrder').val(),
            "menuDescription": $('input#menuDescription').val(),
            "parentId": $('select#parentId').val(),
            "status": $('select#isActive').val()
        };
        postData(data, APP_PATH + "/app/menu/saveOrUpdate/" + is, function (dataResult) {
            if (dataResult.status) {
                $('#modalMenu').modal('toggle');
                success("Data Berhasil Disimpan");
                otable.clear().draw();
            } else {
                danger("Data Gagal Disimpan");
            }
        });
    }

    function validate() {
        $('#modalMenu').bootstrapValidator('validate');
    }

</script>