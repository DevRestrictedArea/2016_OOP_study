<?php
use Wandu\Tmon\Application;

$basePath = dirname(__DIR__);
require $basePath .'/vendor/autoload.php';

date_default_timezone_set('UTC');

$application = new Application($basePath);

$application->bootstrap();
$application->execute();
