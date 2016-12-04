<?php
namespace Wandu\Tmon\Repositories;

use Wandu\Tmon\Models\Good;

class GoodRepository implements RepositoryInterface
{
    /** @var array */
    protected $items = [];

    public function __construct()
    {
        $this->items['H'] = new Good('H', '고급 게이밍 PC', 900000, '고사양 고급 PC');
        $this->items['M'] = new Good('M', '중급 게이밍 PC', 550000, '가격대 성능비 좋은 PC');
        $this->items['L'] = new Good('L', '알뜰 PC', 350000, '최소 구동 환경 제공');
    }

    /**
     * @param $id
     * @return \Wandu\Tmon\Models\Good
     */
    public function getGood($id)
    {
        return isset($this->items[$id]) ? $this->items[$id] : null;
    }
}
