<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h1 style="text-align: center">Информация о пользователе</h1>
    <hr>
    <div style="text-align: center">
        <br>
        <h2>${user.name}</h2>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <br>
        Электронная почта: <b>${user.email}</b>
        <br>
        Адрес: <b>${user.address}</b>
        <br>
        Заказы: <b><#list orders as order>
            <br>${order.event}
        </#list></b>
        <br>
    </div>
</@t.template>