var jquery;
var layer;
layui.use(['jquery', 'layer'], function () {
    jquery = layui.jquery;
    layer = layui.layer;
})

//注册
function register(data, layer, jquery) {
    jquery.ajax({
        type: "POST",
        url: "/register",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(data.field),
        success: function (res) {
            // 注册成功
            if (res.code === 1) {
                layer.msg(res.msg, {icon: 1, offset: '100px'});
                setTimeout(function () {
                    layer.closeAll();
                    window.location.reload()
                }, 1500)

            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error: function (err) {
            console.log("err", err)
        }
    });
}

//登陆
function login(data, layer, jquery) {
    jquery.ajax({
        type: "POST",
        url: "/login",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(data.field),
        success: function (res) {
            // 登陆成功
            if (res.code === 1) {
                layer.msg(res.msg, {icon: 1, offset: '100px'});
                setTimeout(function () {
                    layer.closeAll();
                    window.location.reload()
                }, 1500)

            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error: function (err) {
            console.log("err", err)
        }
    });
}


//打开登陆Layer
function openLoginLayer(layer, jquery) {
    layer.open({
        type: 1,//重要
        title: '登陆',
        resize: false,
        // anim: 4,//左边滚入
        content: jquery('#login')
    });
}

//打开注册Layer
function openRegisterLayer(layer, jquery) {
    layer.open({
        type: 1,//重要
        title: '注册',
        resize: false,
        content: jquery('#register')
    });
}

//打开修改Layer
function openUserInfoLayer() {
    layer.open({
        type: 1,//重要
        title: '个人信息',
        resize: false,
        content: jquery('#userInfo')
    });
}

/**
 * 发布车辆的弹窗
 */
var timestamp;

function openPublishLayer(layer, jquery,form) {
    timestamp = Date.now();
    jquery("#key").val(timestamp);//表单提交时传入Car中（CarController），获取对应的图片
    console.log(timestamp);
    console.log(jquery("#key").val());

    clearLayerData(jquery,form)
    layer.open({
        type: 1,//重要
        title: '发布',
        resize: false,
        content: jquery('#publishCar')
    });
}

//修改车辆信息
//和openPublishLayer相比就是把标题改为了“修改”
function openModifyLayer(layer, jquery) {
    timestamp = Date.now();
    jquery("#key").val(timestamp);//表单提交时传入Car中（CarController），获取对应的图片
    console.log(timestamp);
    console.log(jquery("#key").val());
    layer.open({
        type: 1,//重要
        title: '修改',
        resize: false,
        content: jquery('#publishCar')
    });
}


//提交发布车辆
function publishCar(data, layer, jquery) {
    jquery.ajax({
        type: "POST",
        url: "/publishCar",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(data.field),
        success: function (res) {
            if (res.code === 1) {
                layer.msg(res.msg, {icon: 1, offset: '100px'});
                setTimeout(function () {
                    layer.closeAll();
                    window.location.reload()
                }, 1500)

            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error: function (err) {
            console.log("err", err)
        }
    });
}

// 当前要修改的车辆对象
var modifyCar = {}

/**
 * 获得当前点击修改的车辆信息，赋值给modifyCar，供setLayerData使用
 * @param listId 当前点击项的id
 * @param jquery
 * @param layer
 * @param form 用于setLayerData函数参数
 */
function getModifyCar(listId, jquery, layer, form) {
    jquery.ajax({
        type: "POST",
        url: "/getModifyCar",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(listId),
        success: function (res) {
            // 成功
            // console.log(res)
            // res.data就是要修改的car对象
            modifyCar = res.data
            console.log("modifyCar", modifyCar)
            if (res.code === 1) {
                //先对表单内容进行填充，再打开模态框
                setLayerData(jquery, modifyCar, form)
                // openPublishLayer(layer,jquery)
                //这里的openModifyLayer和openPublishLayer几乎相同，只是标题不同
                openModifyLayer(layer, jquery)
                layer.msg(res.msg, {icon: 1, offset: '100px'});

            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error: function (err) {
            layer.msg(err, {icon: 2, offset: '100px'});
        }
    });
}


/**
 * 上传图片时会覆盖之前的图
 * 可以不用上传，保持原图
 * 此函数会在点击修改时，在打开模态框之前调用，将当前的车辆信息填充到表单中
 * @param jquery
 * @param car 当前点击修改的车辆信息
 * @param form layui的form，用于渲染select，即form.render('select');
 */
function setLayerData(jquery, car, form) {
    jquery("#id").val(car.id)
    jquery("input[name='carId']").val(car.carId)
    jquery("#type option").each(function () {
        if (jquery(this).val() === car.type) {
            jquery(this).attr("selected", "selected");
        }
    });
    form.render('select'); //刷新select选择框渲染
    jquery("input[name='rent']").val(car.rent)
    jquery("input[name='deposit']").val(car.deposit)
    jquery("input[name='address']").val(car.address)
    jquery("input[name='contactName']").val(car.contactName)
    jquery("input[name='contactPhone']").val(car.contactPhone)
    jquery("textarea[name='carDescribe']").val(car.carDescribe)
}

//如果在发布之前点击了修改，那么表单元素会被setData，这时点发布会填充信息
//应在发布逻辑中调用此函数，清空表单元素的数据
function clearLayerData(jquery,form) {
    jquery("#id").val('')
    jquery("input[name='carId']").val('')
    jquery("#type option:first").prop("selected", 'selected');
    form.render('select'); //刷新select选择框渲染
    jquery("input[name='rent']").val('')
    jquery("input[name='deposit']").val('')
    jquery("input[name='address']").val('')
    jquery("input[name='contactName']").val('')
    jquery("input[name='contactPhone']").val('')
    jquery("textarea[name='carDescribe']").val('')
}

//下架
//未出租时才能下架
function downCar(jquery, listId, layer) {
    jquery.ajax({
        type: "POST",
        url: "/downCar",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(listId),
        success: function (res) {
            if (res.code === 1) {
                layer.msg(res.msg, {icon: 1, offset: '100px'});
                setTimeout(function () {
                    layer.closeAll();
                    window.location.reload();
                }, 1500)
            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error: function (err) {
            layer.msg(err, {icon: 2, offset: '100px'});
        }
    });
}

//上架
//已下架时才能上架
function upCar(jquery, listId, layer) {
    jquery.ajax({
        type: "POST",
        url: "/upCar",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(listId),
        success: function (res) {
            if (res.code === 1) {
                layer.msg(res.msg, {icon: 1, offset: '100px'});
                setTimeout(function () {
                    layer.closeAll();
                    window.location.reload();
                }, 1500)
            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error: function (err) {
            layer.msg(err, {icon: 2, offset: '100px'});
        }
    });
}

//删除
function deleteCar(jquery, listId, layer) {
    jquery.ajax({
        type: "POST",
        url: "/deleteCar",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(listId),
        success: function (res) {
            if (res.code === 1) {
                layer.msg(res.msg, {icon: 1, offset: '100px'});
                setTimeout(function () {
                    layer.closeAll();
                    window.location.reload();
                }, 1500)
            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error: function (err) {
            layer.msg(err, {icon: 2, offset: '100px'});
        }
    });
}

//收藏
function submitMark(id, jquery, layer) {
    jquery.ajax({
        type: 'POST',
        url: '/submitMark',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(id),
        success: function (res) {
            console.log(res)
            if (res.code === 1) {
                if(res.msg==='收藏成功'){
                    layer.msg(res.msg)
                    jquery("#mark").text("已收藏")
                }else{
                    layer.msg(res.msg)
                    jquery("#mark").text("收藏")
                }
            } else {
                layer.msg(res.msg)
            }
        },
        error: function (err) {
            layer.msg(err, {icon: 2, offset: '100px'});
        }
    })
}

//首次进入详情页，查询当前用户是否已经收藏该车辆
function isMarked(id, jquery) {
    jquery.ajax({
        type: 'POST',
        url: '/isMarked',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(id),
        success: function (res) {
            if (res.code === 1) {
                jquery("#mark").text("已收藏")
            } else {
                jquery("#mark").text("收藏")
            }
        },
        error: function (err) {
            layer.msg(err, {icon: 2, offset: '100px'});
        }
    })
}

//提交订单
function submitOrder(id, enddate, jquery, layer) {
    jquery.ajax({
        type: 'POST',
        url: '/submitOrder',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify({
            id: id,
            enddate: enddate
        }),
        success: function (res) {
            if (res.code === 1) {
                layer.msg(res.msg);
                //订单创建成功，跳到合同页面
                setTimeout(function () {
                    window.location.href = '/order/agreement/view?orderId=' + res.data
                }, 1500)

            } else {
                layer.msg(res.msg);
            }
        },
        error: function (err) {
            layer.msg("请求失败", {icon: 2, offset: '100px'});
        }
    })
}

//签订合同
function confirmAgreement(orderId) {
    jquery.ajax({
        type: 'POST',
        url: '/confirmAgreement',
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(orderId),
        success: function (res) {
            if (res.code === 1) {
                layer.msg(res.msg, {icon: 1, offset: '100px'});
                setTimeout(function () {
                    // window.location.href = '/order/agreement/view?orderId='+res.data
                    window.location.href = '/order/pay?orderId=' + res.data
                }, 3000)
            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error: function (err) {
            layer.msg(err, {icon: 2, offset: '100px'});
        }
    })
}

function submitPay(orderId) {
    jquery.ajax({
        type: 'POST',
        url: '/submitPay',
        async: false,
        contentType: 'application/json',
        data:JSON.stringify(orderId),
        success:function (res) {
            if (res.code === 1) {
                layer.msg(res.msg, {icon: 1, offset: '100px'});
                setTimeout(function () {
                    window.location.href = '/carOrderPage'
                }, 1500)
            } else {
                layer.msg(res.msg, {icon: 2, offset: '100px'});
            }
        },
        error:function (err) {
            layer.msg(err, {icon: 2, offset: '100px'});
        }
    })
}


function cancelOrder(orderId,table) {
    jquery.ajax({
        type: 'POST',
        url: '/cancelOrder',
        async: false,
        contentType: 'application/json',
        data:JSON.stringify(orderId),
        success:function (res) {
            if (res.code === 1) {
                layer.msg(res.msg);
                table.reload('orderTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                }); //只重载数据
            } else {
                layer.msg(res.msg);
            }
        },
        error:function (err) {
            layer.msg(err);
        }
    })
}

//退租
function endOrder(orderId,table) {
    jquery.ajax({
        type: 'POST',
        url: '/endOrder',
        async: false,
        contentType: 'application/json',
        data:JSON.stringify(orderId),
        success:function (res) {
            if (res.code === 1) {
                layer.msg(res.msg);
                table.reload('orderTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                }); //只重载数据
            } else {
                layer.msg(res.msg);
            }
        },
        error:function (err) {
            layer.msg(err);
        }
    })
}

//取消退租
function cancelEndOrder(orderId,table) {
    jquery.ajax({
        type: 'POST',
        url: '/cancelEndOrder',
        async: false,
        contentType: 'application/json',
        data:JSON.stringify(orderId),
        success:function (res) {
            if (res.code === 1) {
                layer.msg(res.msg);
                table.reload('orderTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                }); //只重载数据
            } else {
                layer.msg(res.msg);
            }
        },
        error:function (err) {
            layer.msg(err);
        }
    })
}


//退租申请
//tag：1表示同意，0表示拒绝
function endOrderHandle(orderId,tag,table) {
    jquery.ajax({
        type: 'POST',
        url: '/endOrderHandle',
        async: false,
        contentType: 'application/json',
        data:JSON.stringify({orderId,tag}),
        success:function (res) {
            if (res.code === 1) {
                layer.msg(res.msg);
                table.reload('orderTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                }); //只重载数据
            } else {
                layer.msg(res.msg);
            }
        },
        error:function (err) {
            layer.msg(err);
        }
    })
}

