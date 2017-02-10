def costinfo(bkCoupon, wishes):
	cost = 0

	for item in wishes:
		total = 0
		total += item.goodobj.price * item.count

		print(item.goodobj.code + " " + str(item.count) +"개 할인 전 금액: " + str(total) + "원, ", end=" ")
		

		for couponitem in item.couponobj:
			if couponitem.code == "C1":
				total -= couponitem.discount
			if couponitem.code == "C1-1":
				total -= couponitem.discount * item.count

		# 잘못된 예제 같지만 장바구니 쿠폰도 상품 쿠폰에 사용한 예시가 있어 추가함
		for couponitem in item.couponobj:
			if couponitem.code == "C2":
				total -= total * couponitem.discount
			if couponitem.code == "C3":
				total -= total * couponitem.discount

		print("할인 후 금액: " + str(total) + "원"
				+ " 적용된 쿠폰: ", end=" ")
		item.printCoupon()
		print()


		cost += total


	print("전체 금액: " + str(cost) + "원 / ", end=" ")



	# 전체 장바구니 쿠폰 할인 
	for couponitem in bkCoupon:
		if couponitem.code == "C2":
			cost -= cost * couponitem.discount
		if couponitem.code == "C3":
			cost -= cost * couponitem.discount

	print("총 할인 금액: " + str(cost) + "원\n")


def coupon_err(bkCoupon, coupon, wishes, totalcost):
	if coupon.code == "C1":
		c1_condition(bkCoupon, wishes)
	if coupon.code == "C1-1":
		c1_1_condition(bkCoupon, wishes)
	if coupon.code == "C2":
		c2_condition(bkCoupon, wishes, totalcost)
	if coupon.code == "C3":
		c3_condition(bkCoupon)

def c1_condition(bkCoupon, wishes):

	if wishes.goodobj.code is not "L":
		print("C1 쿠폰은 알뜰 PC에 사용 가능한 쿠폰입니다.")
	if wishes.count != 1:
		print("C1 쿠폰은 1개만 적용 가능한 쿠폰입니다.")
	if len(wishes.couponobj) + len(bkCoupon) > 1:
		print("C1 쿠폰은 중복 사용으로 인해 사용이 불가능합니다.")


def c1_1_condition(bkCoupon, wishes):
	if wishes.goodobj.code is not "L":
		print("C1 쿠폰은 알뜰 PC에 사용 가능한 쿠폰입니다.")
	if wishes.count < 5 or wishes.count > 10:
		print("C1 쿠폰은 5 ~ 10대까지만 적용 가능합니다.")
	if len(wishes.couponobj) + len(bkCoupon) > 1:
		print("C1 쿠폰은 중복 사용으로 인해 사용이 불가능합니다.")



def c2_condition(bkCoupon, wishes, totalcost):
	if totalcost < 300000:
		print("C2 쿠폰은 이 장바구니의 금액이 300,000원 이상일 때 사용 가능합니다.")
	if len(wishes.couponobj) + len(bkCoupon) > 1:
		print("C2 쿠폰은 중복 사용으로 인해 사용이 불가능합니다.")



def c3_condition(bkCoupon):
	"""
	for item in bkCoupon:
		# 단일 스레드가 아닐 경우 lock
		if limit > 0:
			limit -= 1
		else:
			print("C3 쿠폰은 선착순 10명에게만 사용 가능합니다.")
	"""
	pass





class Coupon(object):

	def __init__(self, code, name, discount):
		self.code = code
		self.name = name
		self.discount = discount
