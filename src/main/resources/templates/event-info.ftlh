<#import "blocks/template.ftlh" as t>
<@t.template user>
    <p><h1 style="text-align: center">${event.title} | ${event.price} ₽</h1></p>
    <p style="text-align: center">${event.description}</p>
    <p style="text-align: center">${event.dateTime}</p>
    <hr>
    <p style="text-align: center">
    <form action="/order/${event.ID}" method="post">
        <input class="btn btn-light my-2 my-sm-0" type="submit" value="Купить билет на мероприятие"/>
    </form>
    </p>
</@t.template>