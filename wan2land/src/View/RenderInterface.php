<?php
namespace Wandu\Tmon\View;

interface RenderInterface
{
    /**
     * @param string $template
     * @param array $values
     * @return string
     */
    public function render($template, array $values = []);
}
