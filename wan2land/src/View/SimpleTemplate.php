<?php
namespace Wandu\Tmon\View;

class SimpleTemplate implements RenderInterface
{
    /** @var string $path */
    protected $path;

    /**
     * @param string $path
     */
    public function __construct($path)
    {
        $this->path = $path;
    }

    /**
     * {@inheritdoc}
     */
    public function render($template, array $values = [])
    {
        ob_start();

        extract($values);
        require "{$this->path}/{$template}.php";
        $contents = ob_get_contents();
        ob_end_clean();
        return $contents;
    }
}
