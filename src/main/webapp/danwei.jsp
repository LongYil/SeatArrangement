<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-02-04
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %><!-- 引入标签库  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>用户单位</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
    <style type="text/css">
        tr{
            height: 43px;
        }
    </style>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户单位</h5>
                </div>
                <div class="ibox-content">
                    <div class="">
                        <a onclick="addDanWei();" href="javascript:void(0)" class="btn btn-primary ">添加单位</a>
                    </div>
                    <table class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th style="width: 5%">序号</th>
                            <th style="width: 7%">学校编号</th>
                            <th>学校名称</th>
                            <th>学校简介</th>
                            <th style="width: 15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="listxuexiao" status="stc">
                            <tr class="gradeX">
                                <td>${stc.index+1}</td>
                                <td><s:property value="XXBH"/></td>
                                <td><s:property value="XXMC"/></td>
                                <td><s:property value="XXJC"/></td>
                                <td>
                                    <a onclick="viewXiaoQu('<s:property value="XXBH"/>')" href="javascript:void(0)" class="btn btn-white btn-sm"><i class="fa fa-search"></i> 查看校区 </a>
                                    <a onclick="removeDanWei('<s:property value="XXBH"/>','<s:property value="XXMC"/>')" href="javascript:void(0)" class="btn btn-white btn-sm"><i class="fa fa-trash"></i> 删除 </a>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>

<script src="js/plugins/layer/layer.min.js"></script>
<script src="js/content.js?v=1.0.0"></script>
<!-- Data Tables -->
<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="layui.all.js"></script>
<script src="js/ajaxcommunicate.js"></script>
<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>

<script type="text/javascript">
    function removeDanWei(arg1,arg2) {
        parent.layer.confirm('确定删除学校:' + arg2 + '？', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            var text = ajaxSubmit("xuexiao_delete.action",arg1);
            if(text=="1"){
                parent.layer.msg('删除成功', {icon: 1});
                window.location="xuexiao_findAll.action";
            }else{
                parent.layer.msg('删除失败', {icon: 2});
            }
        }, function(){
            parent.layer.msg('已取消', {shift: 6});
        });
    }
    function viewXiaoQu(arg) {
        window.location="xiaoqu_findAll?XXBH=" + arg;
    }

</script>
<!-- Page-Level Scripts -->
<script type="text/javascript">
    $(document).ready(function () {
        $('.dataTables-example').dataTable();

        /* Init DataTables */
        var oTable = $('#editable').dataTable();

        /* Apply the jEditable handlers to the table */
        oTable.$('td').editable('../example_ajax.php', {
            "callback": function (sValue, y) {
                var aPos = oTable.fnGetPosition(this);
                oTable.fnUpdate(sValue, aPos[0], aPos[1]);
            },
            "submitdata": function (value, settings) {
                return {
                    "row_id": this.parentNode.getAttribute('id'),
                    "column": oTable.fnGetPosition(this)[2]
                };
            },

            "width": "90%",
            "height": "100%"
        });


    });

</script>

</body>

</html>
