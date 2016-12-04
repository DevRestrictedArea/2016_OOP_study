<?php require __DIR__ . '/header.php'; ?>

<main>
    <form action="/cart" method="post">
        <div class="input">
            <input type="text" name="cart_id" placeholder="카트번호" autofocus />
        </div>
        <button type="submit">전송</button>
    </form>
    <?php if (isset($cart)) : ?>
<pre>
장바구니ID : <?php echo $cart->getId() ?>

사용자 ID : <?php echo $cart->getUsername() ?>

사용한 쿠폰들 :

<?php foreach ($cart->getCartCoupons() as $coupon) : ?>
    <?php echo $coupon->getId(); ?>
<?php endforeach ?>


주문들:

<?php foreach ($cart->getOrders() as $order) : ?>
    <?php echo $order->getGood()->getId(); ?>, <?php echo $order->getCount(); ?>개 * <?php echo $order->getGood()->getPrice(); ?>, 쿠폰 : <?php if ($order->getCoupon()) : ?><?php echo $order->getCoupon()->getId() ?> 적용<?php endif; ?>
<?php endforeach ?>


전체 가격 : <?php echo $cart->getTotalPrice() ?>원
</pre>
    <?php endif; ?>
</main>

<?php require __DIR__ . '/footer.php'; ?>

