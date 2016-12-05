<%--
  User: 曲修成
  Date: 2016/11/17
  Time: 14:15
--%>

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctxStatic}/ace/assets/js/jquery.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
window.jQuery || document.write("<script src='${ctxStatic}/ace/assets/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if('ontouchstart' in document.documentElement) {
        document.write("<script src='${ctxStatic}/ace/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
    }
</script>

<script src="${ctxStatic}/ace/assets/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="${ctxStatic}/ace/assets/js/excanvas.js"></script>
<![endif]-->
<script src="${ctxStatic}/ace/assets/js/jquery-ui.custom.js"></script>
<script src="${ctxStatic}/ace/assets/js/jquery.ui.touch-punch.js"></script>
<script src="${ctxStatic}/ace/assets/js/jquery.easypiechart.js"></script>
<script src="${ctxStatic}/ace/assets/js/jquery.sparkline.js"></script>
<script src="${ctxStatic}/ace/assets/js/flot/jquery.flot.js"></script>
<script src="${ctxStatic}/ace/assets/js/flot/jquery.flot.pie.js"></script>
<script src="${ctxStatic}/ace/assets/js/flot/jquery.flot.resize.js"></script>

<!-- ace scripts -->
<script src="${ctxStatic}/ace/assets/js/ace/elements.scroller.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.colorpicker.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.fileinput.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.typeahead.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.wysiwyg.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.spinner.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.treeview.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.wizard.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.aside.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.ajax-content.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.touch-drag.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.sidebar.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.submenu-hover.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.widget-box.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.widget-on-reload.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.searchbox-autocomplete.js"></script>
<script src="${ctxStatic}/ace/assets/js/jquery-ui.js"></script>
<script src="${ctxStatic}/ace/assets/js/bootbox.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {

        //override dialog's title function to allow for HTML titles
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function(title) {
                var $title = this.options.title || '&nbsp;'
                if( ("title_html" in this.options) && this.options.title_html == true )
                    title.html($title);
                else title.text($title);
            }
        }));

        $.resize.throttleWindow = false;

        var agent = navigator.userAgent.toLowerCase();
        if("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
            $('#tasks').on('touchstart', function(e){
                var li = $(e.target).closest('#tasks li');
                if(li.length == 0)return;
                var label = li.find('label.inline').get(0);
                if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
            });

        $('#tasks').sortable({
                    opacity:0.8,
                    revert:true,
                    forceHelperSize:true,
                    placeholder: 'draggable-placeholder',
                    forcePlaceholderSize:true,
                    tolerance:'pointer',
                    stop: function( event, ui ) {
                        //just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                        $(ui.item).css('z-index', 'auto');
                    }
                }
        );
        $('#tasks').disableSelection();
        $('#tasks input:checkbox').removeAttr('checked').on('click', function(){
            if(this.checked) $(this).closest('li').addClass('selected');
            else $(this).closest('li').removeClass('selected');
        });


        //show the dropdowns on top or bottom depending on window height and menu position
        $('#task-tab .dropdown-hover').on('mouseenter', function(e) {
            var offset = $(this).offset();

            var $w = $(window)
            if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
                $(this).addClass('dropup');
            else $(this).removeClass('dropup');
        });

    })
</script>

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="${ctxStatic}/ace/assets/css/ace.onpage-help.css" />
<link rel="stylesheet" href="${ctxStatic}/ace/docs/assets/js/themes/sunburst.css" />

<script type="text/javascript"> ace.vars['base'] = '${ctxStatic}/ace'; </script>
<script src="${ctxStatic}/ace/assets/js/ace/elements.onpage-help.js"></script>
<script src="${ctxStatic}/ace/assets/js/ace/ace.onpage-help.js"></script>
<script src="${ctxStatic}/ace/docs/assets/js/rainbow.js"></script>
<script src="${ctxStatic}/ace/docs/assets/js/language/generic.js"></script>
<script src="${ctxStatic}/ace/docs/assets/js/language/html.js"></script>
<script src="${ctxStatic}/ace/docs/assets/js/language/css.js"></script>
<script src="${ctxStatic}/ace/docs/assets/js/language/javascript.js"></script>

<%-- dataTables --%>
<script src="${ctxStatic}/ace/assets/js/jquery.dataTables.js"></script>
<script src="${ctxStatic}/ace/assets/js/jquery.dataTables.bootstrap.js"></script>

<!-- 定制列展示 -->
<script src="${ctxStatic}/jquery.column/jquery.cookie.js" type="text/javascript"></script>

<!-- zTree js -->
<script type="text/javascript" src="${ctxStatic}/zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctxStatic}/zTree_v3-master/js/jquery.ztree.excheck.js"></script>

<%--treeTable--%>
<script src="${ctxStatic}/treetable/3.2.0/jquery.treetable.js" type="text/javascript"></script>

<%--添加整体js--%>
<script src="${ctxStatic}/tooltip/tooltip.js" type="text/javascript"></script>
