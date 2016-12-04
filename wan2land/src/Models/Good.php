<?php
namespace Wandu\Tmon\Models;

use InvalidArgumentException;

class Good
{
    /** @var string */
    protected $id;

    /** @var int */
    protected $price;

    /** @var string */
    protected $name;

    /** @var string */
    protected $description;

    /**
     * @param string $id
     * @param string $name
     * @param int $price
     * @param string $description
     */
    public function __construct($id, $name, $price, $description = '')
    {
        if (!is_string($id)) {
            throw new InvalidArgumentException('코드는 반드시 문자열여야 합니다');
        }
        if (!is_string($name)) {
            throw new InvalidArgumentException('이름은 반드시 문자열여야 합니다');
        }
        if (!is_int($price)) {
            throw new InvalidArgumentException('가격은 반드시 숫자여야 합니다');
        }
        if (!is_string($description)) {
            throw new InvalidArgumentException('설명은 반드시 문자열여야 합니다');
        }
        $this->id = $id;
        $this->price = $price;
        $this->name = $name;
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return int
     */
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }
}