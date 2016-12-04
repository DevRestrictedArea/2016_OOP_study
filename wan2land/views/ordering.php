<?php require __DIR__ . '/header.php'; ?>

<pre>
장바구니ID : <?php echo $cart->getId() ?>

사용자 ID : <?php echo $cart->getUsername() ?>

사용한 쿠폰들 :

<?php foreach ($cart->getCartCoupons() as $coupon) : ?>
    <?php echo $coupon->getId(); ?>
<?php endforeach ?>


주문들:

<?php foreach ($cart->getOrders() as $order) : ?>
    <?php echo $order->getGood()->getId(); ?>, <?php echo $order->getCount(); ?>개, 쿠폰 : <?php if ($order->getCoupon()) : ?><?php echo $order->getCoupon()->getId() ?><?php endif; ?>
<?php endforeach ?>
</pre>

<?php require __DIR__ . '/footer.php'; ?>