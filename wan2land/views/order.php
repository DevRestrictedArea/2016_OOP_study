<?php require __DIR__ . '/header.php'; ?>

<main>
    <form action="/order" method="post">
        <div class="input">
            <input type="text" name="order" value="123456,tmon,C3,H|1|C2" placeholder="123456,tmon,C3,H|1|C2" autofocus />
        </div>
        <button type="submit">전송</button>
    </form>
</main>

<?php require __DIR__ . '/footer.php'; ?>

