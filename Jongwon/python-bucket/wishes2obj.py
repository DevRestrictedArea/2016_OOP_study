class Wishes2Obj(object):

	def __init__(self, goodobj, count, couponobj):
		self.goodobj = goodobj
		self.count = count
		self.couponobj = couponobj

	def printCoupon(self):
		couponList = list()
		for item in self.couponobj:
			couponList.append(item.code)
			print(item.code, end=" ")
		return couponList


