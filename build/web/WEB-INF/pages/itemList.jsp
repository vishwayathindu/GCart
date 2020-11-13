<%-- 
    Document   : itemList
    Created on : Oct 15, 2020, 12:03:52 PM
    Author     : vishwa_p
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-jquery-grid-tags" prefix="sjg" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.11/lodash.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
        <script>
            function addtocartformatter(cellvalue, options, rowObject) {
                return "<a href='javascript:void(0)' onClick='javascript:addItemCart(&#34;" + cellvalue + "&#34;)'>Add to cart</a>";
            }

            
            var cartItem = [];
            
            let globaleTotal = 0.0;
       
            function firstOccurance(data){
                    cartItem.push({
                        itemId:data.itemId,
                        itemName: data.itemName,
                        quntity: 1,
                        unitPrice: data.unitPrice,
                        discount:data.discount,
                        });
                                    
                    const row = "<tr>\n\
                                <td>"+ data.itemName +"</td> \n\
                                <td>"+ 1 +"</td> \n\
                                <td>"+ data.unitPrice +"</td> \n\
                                <td>"+ data.discount +"</td> \n\
                                </tr> ";
                                $('#myTable').append(row);
                    }
                            
            
            function payTotal(){
                var data1 = JSON.stringify(cartItem);
                
                
                
                $.ajax({
                    url: '${pageContext.request.contextPath}/payForItem.blb',
                    data:{cartData: data1},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        window.location.reload();
                        
                    }
                
            });}
            
            function pdf(){
                
                form = document.getElementById('editform');
                form.action = 'pdfrItem.blb';
                form.submit();
//                $.ajax({
//                    url: '${pageContext.request.contextPath}/pdfItem.blb',
//                    dataType: "json",
//                    type: "POST",
//                    success: function () {
//                        window.location.reload();
//                        
//                    }
//                
//            })
            ;}
            
            
            function addItemCart(keyval) {
            let squntity = 1;
            if (cartItem.length===0){
            }else{
                console.log("cart lrngth "+ cartItem.length );
                for(let i = 0; i < cartItem.length; i++){
                    console.log("cart id "+cartItem[i].itemId);
                    console.log("item id "+ keyval);
                    console.log(cartItem);
                    if(cartItem[i].itemId == keyval){
                        squntity=(cartItem[i].quntity+1);
                        
                      }
                }
            }  
            
                $.ajax({

                    url: '${pageContext.request.contextPath}/takeItem.blb',
                    data: {itemId: keyval,
                           quntity: squntity,
                       },
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#editj').empty();
                        var msg = data.message;
                        if (msg) {
                            alert(msg);
                        } else {


                            for(let i = 0; i < cartItem.length; i++){
                               
                                if(cartItem[i].itemId === data.itemId){
                                    
                                    $('#myTable').children().remove();
                                    
                                    cartItem[i].quntity = (cartItem[i].quntity+1);
                                    cartItem[i].discount=data.discount;
                                    
                                    
                                     cartItem.forEach(a => {
                                        const row = "<tr>\n\
                                            <td>"+ a.itemName +"</td> \n\
                                            <td>"+ a.quntity +"</td> \n\
                                            <td>"+ a.unitPrice +"</td> \n\
                                            <td>"+ a.discount +"</td> \n\
                                            </tr> ";
                                        $('#myTable').append(row);
                                        
//                                        
                                    });
                                    totalCalulation();
                                    break;
                                }else{
                                    if(i==cartItem.length-1){
                                        firstOccurance(data);
                                        totalCalulation();
                                        break;
                                    }
                                    totalCalulation();
                                }
                                 
                            }
                            
                            
                            if(cartItem.length===0){
                                firstOccurance(data);
                                totalCalulation();
                            }
                            function totalCalulation(){
                                let total = 0.0;
                                for (var item of cartItem) {
                                        total+= (item.quntity * item.unitPrice)-item.discount;
                                }
                                
                                $('#total').text(total);
                                globaleTotal=total;
                            }
                            

                            
// if you want to insert this table in a div with id attribute 
// set as "myDiv", you can do this:
                            


                        }
                    }

                });
            }
            

            
            
        </script>
        <sj:head jqueryui="true" jquerytheme="redmond"/>
        <style>
            table, th, td {    
                border: 1px solid black;
                
            }
        </style>


    </head>
    <body>
        <h1>ITEM DETAILS</h1>

        <div id="formstyle">

            <s:form action="#" theme="simple" method="post" id="editform">
                <table>

                    <tr><td>Item name </td><td><s:textfield id="itemName" name="itemName" maxLength="15" required="true"/></td></tr>                 
                    <tr><td>quantity </td><td><s:textfield id="quntity" name="quntity" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" /></td></tr>
                    <tr><td>unit price </td><td><s:textfield id="unitPrice" name="unitPrice" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" /></td></tr>
                    <tr><td>Discount type</td><td><s:select  id="discountType" name="discountType" list="#{'unite':'unite','quntity':'quntity'}"/></td></tr>
                    <tr><td>Discount Method</td><td><s:select  id="discountMethord" name="discountMethord" list="#{'flat':'falt','percentage':'percentage'}"/></td></tr>
                    <tr><td>Discount</td><td><s:textfield id="discount" name="discount"  /></td></tr>


                    <tr>
                        <td></td>
                        <td>
                            <s:url var="editurl" action="addItem"/>

                            <sj:submit 
                                button="true" 
                                value="ADD ITEM" 
                                href="%{editurl}" 
                                targets="message" 
                                onclick="window.location.href=window.location.href"
                                />

                        </td>
                    </tr>

                </table>
            </s:form>
        </div>
        <div>

            <s:url var="dataList" action="retriveItem" />
            <sjg:grid
                id="gridtable"
                caption="User Details"
                dataType="json"
                href="%{dataList}"
                pager="true"
                gridModel="gridModel"
                rowList="10,20,30,40"
                rowNum="10"
                autowidth="true"
                rownumbers="true"
                rowTotal="false"
                viewrecords="true"
                >
                <sjg:gridColumn name="itemName" index="itemName" title="Item Name"/>
                <sjg:gridColumn name="quntity" index="quntity" title="quantity"/>
                <sjg:gridColumn name="unitPrice" index="unitPrice" title="unitPrice"/>
                <sjg:gridColumn name="discountType" index="discountType" title="Discount Type"/>
                <sjg:gridColumn name="discountMethord" index="discountMethord" title="Discount Methord"/>
                <sjg:gridColumn name="discount" index="discount" title="Discount"/>
                <sjg:gridColumn name="itemId" index="itemId" title="add to card"  align="center"  formatter="addtocartformatter"   />



            </sjg:grid>
        </div>         
<table>
                        <thead>
                        <tr>
                            <th>Card Name</th>
                            <th>Card Qty</th>
                            <th>Card unit price</th>
                            <th>Discount</th>
                        </tr>
                        </thead>
                        <tbody id="myTable">
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="2">Total :</td>
                            <td id="total">00.00</td>
                        </tr>
                        <tr>
                        <button onclick="payTotal()">pay</button>
                        <button onclick="pdf()">pdf</button>
                        </tr>
                        </tfoot>
                    </table>

    </body>
</html>
