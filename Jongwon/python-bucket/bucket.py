class Bucket(object):

	def __init__(self, bucketID, userID, bkCoupon, wishes):
		self.bucketID = bucketID
		self.userID = userID
		self.bkCoupon = bkCoupon
		self.wishes = wishes
		


	def totalcost(self):
		cost = 0
		for item in self.wishes:
			cost += item.goodobj.price * item.count
		return cost





