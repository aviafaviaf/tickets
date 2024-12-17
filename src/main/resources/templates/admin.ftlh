<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Панель администратора</h1><hr>
    <h3>Создать мероприятие</h3>
    <form action="admin/event/create" method="post" enctype="multipart/form-data">
        Название мероприятие: <input type="text" name="title"><br><br>
        Дата мероприятия: <input type="datetime-local" name="dateTime"><br><br>
        Описание мероприятия: <textarea name="description"></textarea><br><br>
        Цена билета: <input type="number" name="price"><br><br>
        <input type="submit" value="Добавить мероприятие"/><br>
    </form>
    <h3>Удалить мероприятие</h3>
    <form action="admin/event/delete" method="post">
        <input type="number" name="id"/>
        <input type="submit" value="Удалить">
    </form>
</@t.template>