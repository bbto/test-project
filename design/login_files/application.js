// Place your application-specific JavaScript functions and classes here
// This file is automatically included by javascript_include_tag :defaults

var first_selected_source="";
var first_selected_file ="";
var sw_show_log = true;
var sw_show_error = true;
var search_field="";
var sw=true;
var show_records_confirmation = "0";
var show_records_type="";
var go_to_line_number = "0";

function hook()
{
    return true;
}

function change_anchor_title(element, anchor)
{
    if(element.style.display=="none")anchor.innerHTML="show"
    else anchor.innerHTML="hide"
}
function search_key(e,text, app_id)
{
    var key_=e.charCode? e.charCode : e.keyCode

    if(key_==32)
        return false
    else
    {
        if (key_==13)
        {
            find_in_project(text, app_id)
        }
        return true
    }
}
function check_credentials(size)
{
    var check_cred = true;
    var i = 0;
    while(i<size && check_cred)
    {
        if($('chk'+i).checked) check_cred=false
        i++;
    }
    if(check_cred) $('id_Associated').style.display="none";
    else $('id_Associated').style.display="";
}
function search_editor(text)
{
    if (document.getElementById("edit_name").value!="No file loaded")
    {
        var first = true;
        var cursor = editor.getSearchCursor(text, first);
        if(!cursor.findNext()){
            if (document.getElementById("search_count").value != "0")
            {
                editor.jumpToLine_search(0);
                document.getElementById("search_inline").focus();
                document.getElementById("search_count").value=0;
                search_editor(text)
                first = false;
            }
        }
        cursor.select();
    }
}

function move_scroll(element)
{
    $(element).scrollTop = $(element).scrollHeight;
}

function show_search_result()
{
    document.getElementById("show_search_results").style.display="";
    document.getElementById("show_search_results").style.left=((width_/2)-220)+"px";
}
function hide_search_result()
{
    document.getElementById("show_search_results").style.display="none";
}
function show_url_dashboard(obj_url)
{
    document.getElementById("show_url").style.left=((width_/2)-220)+"px";
    document.getElementById("show_url").style.display="";
    $('label_url').innerHTML="Bundle URL"
    document.getElementById("show_url_text").value=document.location.href+"/bundle/"+obj_url;
}
function view_tree_app()
{
    document.getElementById("tree_app_files").style.display="";
    document.getElementById("tree_sources_files").style.display="none";
    document.getElementById("btonclient").className="button_clientn";
    document.getElementById("btnserver").className="button_server";
}

function view_tree_source()
{
    document.getElementById("tree_app_files").style.display="none";
    document.getElementById("tree_sources_files").style.display="";
    document.getElementById("btonclient").className="button_client";
    document.getElementById("btnserver").className="button_servern";
}
function save_button(app_id)
{
    document.getElementById("edit_change").innerHTML="";
    save_file_change(app_id);
}

function show_tree_app()
{
    document.getElementById("folder_menu").style.display="none";
    document.getElementById("source_menu").style.display="none";
    document.getElementById("sroot_menu").style.display="none";
    document.getElementById("nroot_menu").style.display="none";
    document.getElementById("node_menu").style.display="none";

    document.cookie = escape("tree_files") + '=' + escape(0);
    document.getElementById("tree_app_files").style.display="";
    document.getElementById("tree_sources_files").style.display="none";
    document.getElementById("btonclient").className="button_clientn";
    document.getElementById("btnserver").className="button_server";
    $('subscribed_notice_users').hide();
}

function show_tree_source(size)
{

    document.getElementById("folder_menu").style.display="none";
    document.getElementById("source_menu").style.display="none";
    document.getElementById("sroot_menu").style.display="none";
    document.getElementById("nroot_menu").style.display="none";
    document.getElementById("node_menu").style.display="none";

    document.cookie = escape("tree_files") + '=' + escape(1);
    document.getElementById("tree_app_files").style.display="none";
    document.getElementById("tree_sources_files").style.display="";
    document.getElementById("btonclient").className="button_client";
    document.getElementById("btnserver").className="button_servern";

    if(size == 0)
        $('subscribed_notice_users').show();
    else
        $('subscribed_notice_users').hide();
}

function hide_url()
{
    document.getElementById("show_url").style.display="none";
}

function insert_url()
{
    var url_title='"'+document.getElementById("url_title").value+'":'+document.getElementById("url_text").value+" ";
    insertbbcode(document.getElementById('faq_body'),url_title,1);
    hide_add_page();
}

function insert_urlpage()
{
    var url_title='"'+document.getElementById("url_titlepage").value+'":/faqs/'+document.getElementById("selected_page").value+" ";
    insertbbcode(document.getElementById('faq_body'),url_title,1);
    hide_add_urlpage();
}

function show_add_urlpage()
{
    document.getElementById("add_urlpage").style.display="";
}

function hide_add_urlpage()
{
    document.getElementById("add_urlpage").style.display="none";
}

function show_add_page()
{
    document.getElementById("add_url").style.display="";
}

function hide_add_page()
{
    document.getElementById("add_url").style.display="none";
}

function detectOS()
{
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("win") != -1)
    {
        return "Windows";
    }
    else if (ua.indexOf("mac") != -1)
    {
        return "Macintosh";
    }
    else if (ua.indexOf("linux") != -1)
    {
        return "Linux";
    }
    else if (ua.indexOf("x11") != -1)
    {
        return "Unix";
    }
    else
    {
        return "Computers";
    }
}

function filter (phrase, _id)
{
    var words = phrase.value.toLowerCase().split(" ");
    var table = document.getElementById(_id);
    var ele;
    for (var r = 1; r < table.rows.length; r++){
        ele = table.rows[r].innerHTML.replace(/<[^>]+>/g,"");
        var displayStyle = 'none';
        for (var i = 0; i < words.length; i++) {
            if (ele.toLowerCase().indexOf(words[i])>=0)
                displayStyle = '';
            else {
                displayStyle = 'none';
                break;
            }
        }
        table.rows[r].style.display = displayStyle;
    }
}

function confirm_ok()
{
    if(show_records_confirmation == "0")
    {
        save_file_change(save_app_id);

        if(save_type != "2")
            open_new_file(url, save_app_id);
        else
            open_new_image(url, save_app_id)
    }
    else
    {
        save_file_change(save_app_id);
        show_records(show_records_confirmation)
    }
}

function confirm_cancel()
{

    if(show_records_confirmation == "0")
    {
        if(save_type != "2")
            open_new_file(url, save_app_id);
        else
            open_new_image(url, save_app_id)
    }
    else
    {
        show_records(show_records_confirmation)
    }
}

function openConfirm(text)
{
    Dialog.confirm(text,
    {
        top: 100,
        width:250,
        className: "alphacube",
        okLabel: "Yes",
        cancelLabel:"No"
    })
}

function exit_before_save(url_node, app_id, type, line)
{
    if(url_node != null)url=url_node;
    if(app_id != null)save_app_id = app_id;
    if(type != null)save_type = type;
    if(line != null)
        go_to_line_number = line;
    else
        go_to_line_number = 0

    if(document.getElementById("edit_change")!=null)
    {
        if(document.getElementById("edit_change").innerHTML=="*")
            var check = document.getElementById("edit_name")

        if(check != null)
        {
            if (document.getElementById("edit_name").value!="No file loaded")
            {
                if(document.getElementById("edit_change").innerHTML=="*")
                {
                    show_records_confirmation = "0";

                    openConfirm("This File have been modified<br/>Save the changes?")

                /*
                   input_box=confirm("Save before exit?");
                    if (input_box==true)
                    {
                        document.getElementById("edit_change").innerHTML="";
                        save_file_change(app_id);
                    }
                    else
                        document.getElementById("edit_change").innerHTML="";
                     */
                }
            }
            else
            {
                document.getElementById("edit_change").innerHTML="";
                if(save_type != "2")
                    open_new_file(url, save_app_id);
                else
                    open_new_image(url, save_app_id)
            }
        }
        else
        {
            document.getElementById("edit_change").innerHTML="";
            if(save_type != "2")
                open_new_file(url, save_app_id);
            else
                open_new_image(url, save_app_id)
        }
    }
}

function hide_show(id, index)
{
    document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? 'block' : 'none';

    if(document.getElementById("arrow"+index) !=null )
    {
        if( document.getElementById(id).style.display=="none")document.getElementById("arrow"+index).src = "/images/orderby_front.png"
        else document.getElementById("arrow"+index).src = "/images/orderby.png"
    }
}


function detect_browser()
{
    var BrowserDetect = {
        init: function () {
            this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
            this.version = this.searchVersion(navigator.userAgent)
            || this.searchVersion(navigator.appVersion)
            || "an unknown version";
            this.OS = this.searchString(this.dataOS) || "an unknown OS";
        },
        searchString: function (data) {
            for (var i=0;i<data.length;i++)	{
                var dataString = data[i].string;
                var dataProp = data[i].prop;
                this.versionSearchString = data[i].versionSearch || data[i].identity;
                if (dataString) {
                    if (dataString.indexOf(data[i].subString) != -1)
                        return data[i].identity;
                }
                else if (dataProp)
                    return data[i].identity;
            }
        },
        searchVersion: function (dataString) {
            var index = dataString.indexOf(this.versionSearchString);
            if (index == -1) return;
            return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
        },
        dataBrowser: [
        {
            string: navigator.userAgent,
            subString: "Chrome",
            identity: "Chrome"
        },
        {
            string: navigator.userAgent,
            subString: "OmniWeb",
            versionSearch: "OmniWeb/",
            identity: "OmniWeb"
        },
        {
            string: navigator.vendor,
            subString: "Apple",
            identity: "Safari",
            versionSearch: "Version"
        },
        {
            prop: window.opera,
            identity: "Opera"
        },
        {
            string: navigator.vendor,
            subString: "iCab",
            identity: "iCab"
        },
        {
            string: navigator.vendor,
            subString: "KDE",
            identity: "Konqueror"
        },
        {
            string: navigator.userAgent,
            subString: "Firefox",
            identity: "Firefox"
        },
        {
            string: navigator.vendor,
            subString: "Camino",
            identity: "Camino"
        },
        {		// for newer Netscapes (6+)
            string: navigator.userAgent,
            subString: "Netscape",
            identity: "Netscape"
        },
        {
            string: navigator.userAgent,
            subString: "MSIE",
            identity: "Explorer",
            versionSearch: "MSIE"
        },
        {
            string: navigator.userAgent,
            subString: "Gecko",
            identity: "Mozilla",
            versionSearch: "rv"
        },
        { 		// for older Netscapes (4-)
            string: navigator.userAgent,
            subString: "Mozilla",
            identity: "Netscape",
            versionSearch: "Mozilla"
        }
        ],
        dataOS : [
        {
            string: navigator.platform,
            subString: "Win",
            identity: "Windows"
        },
        {
            string: navigator.platform,
            subString: "Mac",
            identity: "Mac"
        },
        {
            string: navigator.userAgent,
            subString: "iPhone",
            identity: "iPhone/iPod"
        },
        {
            string: navigator.platform,
            subString: "Linux",
            identity: "Linux"
        }
        ]

    };
    BrowserDetect.init();
}

function insertbbcode(myField, myValue, type)
{
    if (document.selection)
    {
        myField.focus();
        sel = document.selection.createRange();
        sel.text = myValue;
    }
    else if (myField.selectionStart || myField.selectionStart == '0')
    {
        var startPos = myField.selectionStart;
        var endPos = myField.selectionEnd;

        if(startPos != endPos)
        {
            if (type==1)
                myField.value = myField.value.substring(0, startPos)
                + myValue
                + myField.value.substring(startPos, endPos)
                + myValue
                + myField.value.substring(endPos, myField.value.length);
            else
                myField.value = myField.value.substring(0, startPos)
                + "\n"
                + myValue
                + myField.value.substring(endPos, myField.value.length);
        }
        else
            myField.value = myField.value.substring(0, startPos)
            + myValue
            + myField.value.substring(endPos, myField.value.length);
    }
    else
    {
        myField.value += myValue;
    }
    myField.focus();
}

function show_console_logs(app_id){
    if (sw_show_log)
    {
        sw_show_log = false;
        new Ajax.Request('/app_files/view_console_log', {
            asynchronous:true,
            evalScripts:true,
            method:'get',
            onComplete:function(request){
                $('loader_console').toggle();
                $('console_logs').toggle();
                $('console_logs').scrollTo = $('console_logs').scrollHeight;
                $('toggle_logs').blur();
            },
            onLoading:function(request){
                $('loader_console').toggle();
            },
            parameters:{
                authenticity_token: 'encodeURIComponent(d6e52aaef1bc53325a778eb014520c768d863b0e)',
                app_id:app_id
            }
        });
    }
    else
    {
        $('toggle_logs').blur();
        $('console_logs').toggle();
    }
}
function show_error_logs(app_id){
    if (sw_show_error)
    {
        sw_show_error = false;
        new Ajax.Request('/apps/'+app_id+'/sources/source_errors', {
            asynchronous:true,
            evalScripts:true,
            method:'get',
            onComplete:function(request){
//              errors = request.responseText;
//              console.log(errors);
//              errors = (errors == '') ? "There wasn't any errors" : errors ;
                $('output_error').toggle();
//                $('output_error').down('pre').update(errors);
                $('loader_console_error').toggle();
                $('toggleErrors').blur();
            },
            onLoading:function(request){
                $('loader_console_error').toggle();
            },
            parameters:{
                app_id:app_id
            }
        });
    }
    else
    {
        $('toggleErrors').blur();
        $('output_error').toggle();
    }
}

