import numpy as np
import matplotlib.pyplot as plt
import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
from math import e






def main():

	#Working stuff
	rungeKutta(0, 0, 0, 0, 300);
	# plot3D(x, y ,z)
	# fourier(5)
	# temp1 = []
	# temp2 = []
	# for i in xrange(len(x)):
	# 	temp1.append(i)
	# 	temp2.append(i)
	# print(len(x), len(temp2))

	# myFurie()
	
	
	# plot3D(fx, fy, fz)
	# plot3D(x, y, z)
	# plotOneSide2D(fx, temp2)
	# plotOneSide3D(x, temp1, temp2)
	# plot3D(x, y, z)	
	puankare(0, 300);
	plotPuankare()
	# lyapunov(0, 0, 0, 0, 300)




	#Some tests
	# lyapunov(0, 1, 0, 0, 500);
	# print(x)
	# print(y)
	# print(z)
	# plot2D()
	# print(puankareRes)
	# print(len(xx))
	# print(len(yy))
	# print(len(zz))


def plot3D(list1, list2, list3):
	fig = plt.figure()
	ax = fig.gca(projection='3d')
	ax.plot(list1, list2, list3, label='parametric curve')
	plt.show()



def plotOneSide3D(list1, list2, list3):
	fig = plt.figure()
	ax = fig.gca(projection='3d')
	ax.plot(list2, list3, list1, label='parametric curve')
	plt.show()

def plotOneSide2D(l1, l2):
	plt.axis()
	plt.grid(True)
	plt.plot(l1, l2, linewidth=1.0)
	plt.show()


def plot2D():
	plt.axis()
	plt.grid(True)
	plt.plot(x, z, linewidth=1.0)
	plt.show()

def plotPuankare():
	plt.axis([-4, 4, -4, 4])
	plt.plot(xx, yy, 'ro')
	plt.show()

def functionX(t, x, y, z):
	return y + 3*z
def functionY(t, x, y, z):
	return 0.5*x*x - y
def functionZ(t, x, y, z):
	return 1 - 4*x

# def functionX(t, x, y, z):
# 	return y * z
# def functionY(t, x, y, z):
# 	return x * x - y
# def functionZ(t, x, y, z):
# 	return 1 - 4*x

# def functionX(t, x, y, z):
# 	return y*z
# def functionY(t, x, y, z):
# 	return x - y
# def functionZ(t, x, y, z):
# 	return 1 - x*y

def newFX(x, y, z):
	return (y + 3*z)/(1 - 4*x)
def newFY(x, y, z):
	return (0.5*x*x)/(1 - 4*x)
def newFZ(x, y, z):
	return 1
def newFT(x, y, z):
	return 1/(1 - 4*x) 
const = 0
x = []
y = []
z = []
k1_x = []
k1_y = []
k1_z = []
k2_x = []
k2_y = []
k2_z = []
k3_x = []
k3_y = []
k3_z = []
k4_x = []
k4_y = []
k4_z = []
time = []

xx = []
yy = []
zz = []
tt = []
S = []
puankareRes = []

def newRungeKutta(x0, y0, z0, newH, t0):
	xx.append(x0)
	yy.append(y0)
	zz.append(z0)
	tt.append(t0)
	h = newH
	i = 0

	k1_x = newFX(xx[i], yy[i], zz[i])
	k1_y = newFY(xx[i], yy[i], zz[i])
	k1_z = newFZ(xx[i], yy[i], zz[i])
	k1_t = newFT(xx[i], yy[i], zz[i])

	k2_x = newFX(xx[i] + h*k1_x/2.0, yy[i] + h*k1_y/2.0, zz[i] + h*k1_z/2.0)
	k2_y = newFY(xx[i] + h*k1_x/2.0, yy[i] + h*k1_y/2.0, zz[i] + h*k1_z/2.0)
	k2_z = newFZ(xx[i] + h*k1_x/2.0, yy[i] + h*k1_y/2.0, zz[i] + h*k1_z/2.0)
	k2_t = newFT(xx[i] + h*k1_x/2.0, yy[i] + h*k1_y/2.0, zz[i] + h*k1_z/2.0)

	k3_x = newFX(xx[i] + h*k2_x/2.0, yy[i] + h*k2_y/2.0, zz[i] + h*k2_z/2.0)
	k3_y = newFY(xx[i] + h*k2_x/2.0, yy[i] + h*k2_y/2.0, zz[i] + h*k2_z/2.0)
	k3_z = newFZ(xx[i] + h*k2_x/2.0, yy[i] + h*k2_y/2.0, zz[i] + h*k2_z/2.0)
	k3_t = newFT(xx[i] + h*k2_x/2.0, yy[i] + h*k2_y/2.0, zz[i] + h*k2_z/2.0)

	k4_x = newFX(xx[i] + h*k3_x, yy[i] + h*k3_y, zz[i] + h*k3_z)
	k4_y = newFY(xx[i] + h*k3_x, yy[i] + h*k3_y, zz[i] + h*k3_z)
	k4_z = newFZ(xx[i] + h*k3_x, yy[i] + h*k3_y, zz[i] + h*k3_z)
	k4_t = newFT(xx[i] + h*k3_x, yy[i] + h*k3_y, zz[i] + h*k3_z)

	xx.append(xx[i] + h*(k1_x + 2*k2_x + 2*k3_x + k4_x)/6.0)
	yy.append(yy[i] + h*(k1_y + 2*k2_y + 2*k3_y + k4_y)/6.0)
	zz.append(zz[i] + h*(k1_z + 2*k2_z + 2*k3_z + k4_z)/6.0)
	tt.append(tt[i] + h*(k1_t + 2*k2_t + 2*k3_t + k4_t)/6.0)
	

def puankare(alpha, T):

	for i in range(T):
		S.append(z[i] + alpha)

	for i in range(T):
		if(S[i - 1]*S[i] < 0):
			if(S[i-1] > 0):
				H = S[i-1]
			elif(S[i - 1] < 0):
				H = -S[i-1]
			x0 = x[i - 1]
			y0 = y[i - 1]
			z0 = z[i - 1]
			t0 = time[i - 1]
			newRungeKutta(x0, y0, z0, H, t0);

L = 0
def rungeKutta(x0, y0, z0, t0, TT):
	x.append(x0)
	y.append(y0)
	z.append(z0)
	time.append(t0)
	T = TT
	h = 0.05
	i = 0;
	while time[i] < T:
		k1_x.append(functionX(time[i], x[i], y[i], z[i]))
		k1_y.append(functionY(time[i], x[i], y[i], z[i]))
		k1_z.append(functionZ(time[i], x[i], y[i], z[i]))

		k2_x.append(functionX(time[i] + h/2.0, x[i] + h/2.0*k1_x[i], y[i] + h/2.0*k1_y[i], z[i] + h/2.0*k1_z[i]))
		k2_y.append(functionY(time[i] + h/2.0, x[i] + h/2.0*k1_x[i], y[i] + h/2.0*k1_y[i], z[i] + h/2.0*k1_z[i]))
		k2_z.append(functionZ(time[i] + h/2.0, x[i] + h/2.0*k1_x[i], y[i] + h/2.0*k1_y[i], z[i] + h/2.0*k1_z[i]))

		k3_x.append(functionX(time[i] + h/2.0, x[i] + h/2.0*k2_x[i], y[i] + h/2.0*k2_y[i], z[i] + h/2.0*k2_z[i]))
		k3_y.append(functionY(time[i] + h/2.0, x[i] + h/2.0*k2_x[i], y[i] + h/2.0*k2_y[i], z[i] + h/2.0*k2_z[i]))
		k3_z.append(functionZ(time[i] + h/2.0, x[i] + h/2.0*k2_x[i], y[i] + h/2.0*k2_y[i], z[i] + h/2.0*k2_z[i]))

		k4_x.append(functionX(time[i] + h/2.0, x[i] + h*k3_x[i], y[i] + h*k3_y[i], z[i] + h*k3_z[i]))
		k4_y.append(functionY(time[i] + h/2.0, x[i] + h*k3_x[i], y[i] + h*k3_y[i], z[i] + h*k3_z[i]))
		k4_z.append(functionZ(time[i] + h/2.0, x[i] + h*k3_x[i], y[i] + h*k3_y[i], z[i] + h*k3_z[i]))

		x.append(x[i] + h*(k1_x[i] + 2*k2_x[i] + 2*k3_x[i] + k4_x[i])/6.0)
		y.append(y[i] + h*(k1_y[i] + 2*k2_y[i] + 2*k3_y[i] + k4_y[i])/6.0)
		z.append(z[i] + h*(k1_z[i] + 2*k2_z[i] + 2*k3_z[i] + k4_z[i])/6.0)
		time.append(time[i] + h);
		i = i + 1;

	global L;
	L = L + 1
	# print(L)
	return [x[i], y[i], z[i]]





# def magicFX(t, x, y, z, X0, Y0, Z0):
# 	return  z*Y0 + y*Z0
# def magicFY(t, x, y, z, X0, Y0, Z0):
# 	return  -X0 - Y0
# def magicFZ(t, x, y, z, X0, Y0, Z0):
# 	return  -y*X0 - x*Y0

def magicFX(t, x, y, z, X0, Y0, Z0):
	return  Y0 + 3*Z0
def magicFY(t, x, y, z, X0, Y0, Z0):
	return  x*X0 + Y0
def magicFZ(t, x, y, z, X0, Y0, Z0):
	return  -4*X0



x_1 = []
y_1 = []
z_1 = []
t_1 = []
k11_x = []
k11_y = []
k11_z = []
k22_x = []
k22_y = []
k22_z = []
k33_x = []
k33_y = []
k33_z = []
k44_x = []
k44_y = []
k44_z = []

def someBlackMagic(T, x0, y0, z0, t0, X, Y, Z):
	x_1.append(x0)
	y_1.append(y0)
	z_1.append(z0)
	t_1.append(t0)
	h = 1
	i = 0;
	while t_1[i] < T:
		# print("called several times")
		k11_x.append(magicFX(t_1[i], x_1[i], y_1[i], z_1[i], X, Y, Z))
		k11_y.append(magicFY(t_1[i], x_1[i], y_1[i], z_1[i], X, Y, Z))
		k11_z.append(magicFZ(t_1[i], x_1[i], y_1[i], z_1[i], X, Y, Z))
		k22_x.append(magicFX(t_1[i] + h/2.0, x_1[i] + h/2.0*k11_x[i], y_1[i] + h/2.0*k11_y[i], z_1[i] + h/2.0*k11_z[i], X, Y, Z))
		k22_y.append(magicFY(t_1[i] + h/2.0, x_1[i] + h/2.0*k11_x[i], y_1[i] + h/2.0*k11_y[i], z_1[i] + h/2.0*k11_z[i], X, Y, Z))
		k22_z.append(magicFZ(t_1[i] + h/2.0, x_1[i] + h/2.0*k11_x[i], y_1[i] + h/2.0*k11_y[i], z_1[i] + h/2.0*k11_z[i], X, Y, Z))
		k33_x.append(magicFX(t_1[i] + h/2.0, x_1[i] + h/2.0*k22_x[i], y_1[i] + h/2.0*k22_y[i], z_1[i] + h/2.0*k22_z[i], X, Y, Z))
		k33_y.append(magicFY(t_1[i] + h/2.0, x_1[i] + h/2.0*k22_x[i], y_1[i] + h/2.0*k22_y[i], z_1[i] + h/2.0*k22_z[i], X, Y, Z))
		k33_z.append(magicFZ(t_1[i] + h/2.0, x_1[i] + h/2.0*k22_x[i], y_1[i] + h/2.0*k22_y[i], z_1[i] + h/2.0*k22_z[i], X, Y, Z))

		k44_x.append(magicFX(t_1[i] + h/2.0, x_1[i] + h*k33_x[i], y_1[i] + h*k33_y[i], z_1[i] + h*k33_z[i], X, Y, Z))
		k44_y.append(magicFY(t_1[i] + h/2.0, x_1[i] + h*k33_x[i], y_1[i] + h*k33_y[i], z_1[i] + h*k33_z[i], X, Y, Z))
		k44_z.append(magicFZ(t_1[i] + h/2.0, x_1[i] + h*k33_x[i], y_1[i] + h*k33_y[i], z_1[i] + h*k33_z[i], X, Y, Z))
		x_1.append(x_1[i] + h*(k11_x[i] + 2*k22_x[i] + 2*k33_x[i] + k44_x[i])/6.0)
		y_1.append(y_1[i] + h*(k11_y[i] + 2*k22_y[i] + 2*k33_y[i] + k44_y[i])/6.0)
		z_1.append(z_1[i] + h*(k11_z[i] + 2*k22_z[i] + 2*k33_z[i] + k44_z[i])/6.0)
		t_1.append(t_1[i] + h);
		i = i + 1;

	return [x_1[i], y_1[i], z_1[i]]
	

def ortogonalization(list1, list2, list3):

	axb = [x*y for x,y in zip(list2, list3)]
	s1 = sum(axb)
	axbxs = [x * s1 for x in list2]

	a3xa1 = [x*y for x,y in zip(list1, list3)]
	s2 = sum(a3xa1)
	a3xa1xa1 = [x * s2 for x in list1]

	a3_axbxs =[x - y for x, y in zip(list3, axbxs)]
	a3_axbxs_a3xa1xa1 = [x - y for x, y in zip(a3_axbxs, list1)]

	sq = [x**2 for x in a3_axbxs_a3xa1xa1]
	s4 = np.sqrt(sum(sq))

	ans = [x/s4 for x in sq]

	return ans
	
	
	
	

def normalize(list):
	newList = [x**2 for x in list]	
	b = np.sqrt(sum(newList))
	anotherFuckingList = [x / b for x in list]
	return anotherFuckingList

def thisIsMadness(list1, list2):
	newList = [x*y for x, y in zip(list1, list2)]
	b = sum(newList)
	anotherFuckingList = [b*x for x in list1]
	thisIsSparta = [x - y for x, y in zip(list2, anotherFuckingList)]
	# newList = [x*y for x, y in zip(list1, list2)]
	# b = sum(newList)
	# thisIsSparta = [b * x for x in list3]
	# anotherFuckingList = [x - y for z, y in zip(list1, thisIsSparta)]
	A = normalize(thisIsSparta)
	return A

def ABxA(list1, list2):
	newList = [x*y for x, y in zip(list1, list2)]
	b = sum(newList)
	A = [x*b for x in list1]
	return A

def triplleKill(list1, list2, list3):
	l1 = ABxA(list1, list3)
	l2 = ABxA(list2, list3)
	l3 = [x - y for x, y in zip(list3, l2)]
	l3 = [x - y for x, y in zip(list3, l1)]
	A = normalize(l3)
	return A

def sqrtSum(list1):
	newList = [x**2 for x in list1]
	a = sum(newList)
	return np.sqrt(a)


def lyapunov(x_0, y_0, z_0, startTime, totalTime):
	x0 = x_0
	y0 = y_0
	z0 = z_0
	t0 = startTime
	TT = totalTime
	T = 1
	Tt0 = 0
	E = 0.4
	k = 1
	N = 10000;
	xx1 = []
	xx2 = []
	xx3 = []
	s1 = []
	s2 = []
	s3 = []

	rungeKutta(x0, y0, z0, t0, TT)
	x1 = [x[TT] - 0.07, y[TT] + 0.5, z[TT] + 0.1]
	x2 = [x[TT] + E, y[TT] + E, z[TT] + E]
	x3 = [x[TT] - E, y[TT] - E, z[TT] - E]
	
	# print(ortogonalization([1, 1, 1], [2, 2, 2]))

	while k < N:
		if(k == 1):
			rungeKutta(x0, y0, z0, t0, TT)

			# x1 = [x[TT] - 1, y[TT] - 1, z[TT] + 2.21]
			# x2 = [x[TT] + E, y[TT] + E, z[TT] + E]
			# x3 = [x[TT] - E, y[TT] - E, z[TT] - E]

			# x1 = [x[TT], y[TT], z[TT]]
			# x2 = [x[TT], y[TT], z[TT]]
			# x3 = [x[TT], y[TT], z[TT]]





			# x1 = [x[TT] + E, y[TT], z[TT]]
			# x2 = [x[TT], y[TT]  + E, z[TT]]
			# x3 = [x[TT], y[TT], z[TT] - E]

			xx1 = normalize(x1)
			xx2 = thisIsMadness(x1, x2)
			xx3 = triplleKill(x1, x2, x3)

			xV1 = someBlackMagic(T, xx1[0], xx1[1], xx1[2], t0, x0, y0, z0)
			xV2 = someBlackMagic(T, xx2[0], xx2[1], xx2[2], t0, x0, y0, z0)
			xV3 = someBlackMagic(T, xx3[0], xx3[1], xx3[2], t0, x0, y0, z0)
		else:
			xV1 = someBlackMagic(T, xx1[k-1], xx1[k-1], xx1[k-1], t0, x0, y0, z0)
			xV2 = someBlackMagic(T, xx2[k-1], xx2[k-1], xx2[k-1], t0, x0, y0, z0)
			xV3 = someBlackMagic(T, xx3[k-1], xx3[k-1], xx3[k-1], t0, x0, y0, z0)

		xxV1 = normalize(xV1)
		xxV2 = thisIsMadness(xV1, xV2)
		xxV3 = ortogonalization(xV1, xV2, xV3)
		
		s1.append(np.log(sqrtSum(xxV1)))
		s2.append(np.log(sqrtSum(xxV2)))
		s3.append(np.log(sqrtSum(xxV3)))

		xx1.append(xx1[1])
		xx1.append(xx1[2])
		xx1.append(xx1[3])
		xx2.append(xx2[1])
		xx2.append(xx2[2])
		xx2.append(xx2[3])
		xx3.append(xx3[1])
		xx3.append(xx3[2])
		xx3.append(xx3[3])

		res = rungeKutta(xxV1, xxV2, xxV3, t0, T)

		x0 = res[0]
		y0 = res[1]
		z0 = res[2]
		TT = 1
		k += 1


	S1 = sum(s1)
	S2 = sum(s2)
	S3 = sum(s3)
	# print(S1, S2, S3)

	omega1 = S1/N*T
	omega2 = S2/N*T
	omega3 = S3/N*T
	print(omega1)
	print(omega2)
	print(omega3)

fx = [] 
fy = [] 
fz = [] 

def fX(t, x, y, z):
	return y/4 + 3*z/4
def fY(t, x, y, z):
	return 0.5*x*x/4 - y/4
def fZ(t, x, y, z):
	return 1 - 4*x/4.0


def eFunc(cons):

	if(cons % 2 == 0):
		return np.cos(np.pi*cons)
	else:
		return np.sin(np.pi * cons)


a = []

fx = []
fy = []
fz = []



def fourier(N):

	xc = np.zeros(len(x), dtype=complex)

	yc = np.zeros(len(y), dtype=complex)

	zc = np.zeros(len(z), dtype=complex)

	sqr2 = np.sqrt(2)
	s = 0
	a_0 = 0
	for i in range(0, 5):
		s += fZ(0, i, 0, 0)
	a.append(s)
	s = 0

	
	for i in range(0, 5):
		#a[1] cosK
		s += fZ(0, i, 0, 0) * sqr2 * np.cos(2 * np.pi * i/4)
	a.append(s)
	s = 0
	
	
	for i in xrange(0,5):
		#a[2]
		s += fZ(0, i, 0, 0) * sqr2 * np.sin(2 * np.pi * i/4)
	a.append(s)
	s = 0
	
	for i in range(0, 5):
		#a[3]
		s += fZ(0, i, 0, 0) * sqr2 * np.cos(4 * np.pi*i/4)
	a.append(s)
	s = 0

	
	for i in range(0, 5):
		#a[4]
		s += fZ(0, i, 0, 0) * sqr2 * np.sin(4 * np.pi*i/4)
	a.append(s)



	for i in range(0, N):
		s+= functionZ(0, i/float(N), 0, 0)
	s = 0;

	print(a)







	for i in range(len(z)):
		fz.append(a[0] + a[1] * sqr2 * np.cos(2 * np.pi * z[i]) + a[2] * sqr2 * np.sin(2*np.pi*z[i]) + a[3]*sqr2*np.cos(4*np.pi*z[i]) + a[4] * sqr2*np.sin(4*np.pi*y[i]) * sqr2*np.sin(4*np.pi*z[i]))





	# b = []



	# a_0 = 0
	# for i in range(0, 5):
	# 	s += fX(0, 0, i, i)
	# b.append(s)
	# s = 0

	
	# for i in range(0, 5):
	# 	s += fX(0, 0, i, i) * sqr2 * np.cos(2 * np.pi * i/4) * sqr2 * np.cos(2 * np.pi * i/4)
	# b.append(s)
	# s = 0
	
	
	# for i in xrange(0,5):
	# 	s += fX(0, 0, i, i) * sqr2 * np.sin(2 * np.pi * i/4) * sqr2 * np.sin(2 * np.pi * i/4)
	# b.append(s)
	# s = 0
	
	# for i in range(0, 5):
	# 	s += fX(0, 0, i, i) * sqr2 * np.cos(4 * np.pi*i/4) * sqr2 * np.cos(4 * np.pi*i/4)
	# b.append(s)
	# s = 0

	
	# for i in range(0, 5):
	# 	s += fX(0, 0, i, i) * sqr2 * np.sin(4 * np.pi*i/4) * sqr2 * np.sin(4 * np.pi*i/4)
	# b.append(s)
	# s = 0;

	# for i in range(len(x)):
	# 	fx.append(b[0]  + b[1] * sqr2 * np.cos(2 * np.pi * y[i]) * sqr2 * np.cos(2 * np.pi * z[i]) + b[2] * sqr2 * np.sin(2*np.pi*y[i]) * sqr2 * np.sin(2*np.pi*z[i]) + b[3] *  sqr2*np.cos(4*np.pi*y[i]) * sqr2*np.cos(4*np.pi*z[i]) + b[4] * sqr2*np.sin(4*np.pi*y[i]) * sqr2*np.sin(4*np.pi*z[i]) ) 





	# l = []


	# a_0 = 0
	# for i in range(0, 5):
	# 	s += fY(0, i, 0, i)
#	 l.append(s)
	# s = 0

	
	# for i in range(0, 5):
	# 	s += fY(0, i, 0, i) * sqr2 * np.cos(2 * np.pi * i/4) * sqr2 * np.cos(2 * np.pi * i/4)
	# l.append(s)
	# s = 0
	
	
	# for i in xrange(0,5):
	# 	s += fY(0, i, 0, i) * sqr2 * np.sin(2 * np.pi * i/4) * sqr2 * np.sin(2 * np.pi * i/4)
	# l.append(s)
	# s = 0
	
	# for i in range(0, 5):
	# 	s += fY(0, i, 0, i) * sqr2 * np.cos(4 * np.pi*i/4) * sqr2 * np.cos(4 * np.pi*i/4)
	# l.append(s)
	# s = 0

	
	# for i in range(0, 5):
	# 	s += fY(0, i, 0, i) * sqr2 * np.sin(4 * np.pi*i/4) * sqr2 * np.sin(4 * np.pi*i/4)
	# l.append(s)
	# s = 0;

	# for i in range(len(x)):
	# 	fy.append(l[0]  + l[1] * sqr2 * np.cos(2 * np.pi * y[i]) * sqr2 * np.cos(2 * np.pi * z[i]) + l[2] * sqr2 * np.sin(2*np.pi*y[i]) * sqr2 * np.sin(2*np.pi*z[i]) +l[3] *  sqr2*np.cos(4*np.pi*y[i]) * sqr2*np.cos(4*np.pi*z[i]) + l[4] * sqr2*np.sin(4*np.pi*y[i]) * sqr2*np.sin(4*np.pi*z[i]) ) 






# vector<dcmplx> Fourier(vector<double> x)
# {
#  int n = x.size();
#  vector<dcmplx> ans(n);
#  for(int i = 0; i < n; i++) 
#  {
#   ans[i].first = 0.0;
#   ans[i].second = 0.0;
#  }

#  for(int i = 0; i < n; i++)
#  {
#   for(int j = 0; j < n; j++)
#   {
#    ans[i].first += x[i] * cos(2.0 * Pi * i * j / (double)n);
#    ans[i].second -= x[i] * sin(2.0 * Pi * i * j / (double)n);
#   }
#  }
#  return ans;
# }


def plotComplex3D(list1, list2, list3):
	# fig = plt.figure()
	# ax = fig.gca(projection='3d')
	# ax.plot(list1)
	plt.axis()
	plt.grid(True)

	plt.plot(list1.real, list3.real, linewidth=1.0)
	plt.plot(x, z, linewidth=1.0)

	plt.show()
	# ax.plot(list1.imag, list2.imag, list3.imag, label='parametric curve')
	# ax.plot((np.real(list1), np.imag(list1)), (np.real(list1), np.imag(list1)), (np.real(list1), np.imag(list1)))
	# ax.plot(np.real(list1), np.imag(list1))
	# ax.plot(np.real(list2), np.imag(list2))
	# ax.plot(np.real(list3), np.imag(list3))


	# ax.plot(np.real(list1), np.imag(list1), np.real(list2), np.imag(list2), np.real(list3), np.real(list3), label='parametric curve')
	# ax.plot(list1, list2, list3, label='parametric curve')
	plt.show()

def myFurie():
	xc = np.zeros(len(x), dtype=complex)

	yc = np.zeros(len(y), dtype=complex)

	zc = np.zeros(len(z), dtype=complex)
	print(len(xc))

	myN = 102.0

	for i in xrange(len(xc)):
		for j in xrange(len(xc)):			
			xc.real[i] += x[i] * np.cos(2.0 * 3.14 * i * j/myN)
			xc.imag[j] -= x[j] * np.sin(2.0 * 3.14 * i * j/myN)	

	print("x finished")

	for i in xrange(len(yc)):
		for j in xrange(len(yc)):			
			yc.real[i] += y[i] * np.cos(2.0 * 3.14 * i * j/myN)
			yc.imag[j] -= y[j] * np.sin(2.0 * 3.14 * i * j/myN)	

	print("y finished")

	for i in xrange(len(zc)):
		for j in xrange(len(zc)):			
			zc.real[i] += z[i] * np.cos(2.0 * 3.14 * i * j/myN)
			zc.imag[j] -= z[j] * np.sin(2.0 * 3.14 * i * j/myN)	
	print("z finished")



	# for i in xrange(len(xc)):
	# 	for j in xrange(len(xc)):			
	# 		xc.real[i] += x[i] * np.cos(2.0 * 3.14 * i * j/myN)
	# 		xc.imag[j] -= x[j] * np.sin(2.0 * 3.14 * i * j/myN)	

	# print("x finished")

	# for i in xrange(len(yc)):
	# 	for j in xrange(len(yc)):			
	# 		yc.real[i] += y[i] * np.cos(2.0 * 3.14 * i * j/myN)
	# 		yc.imag[j] -= y[j] * np.sin(2.0 * 3.14 * i * j/myN)	

	# print("y finished")

	# for i in xrange(len(zc)):
	# 	for j in xrange(len(zc)):			
	# 		zc.real[i] += z[i] * np.cos(2.0 * 3.14 * i * j/myN)
	# 		zc.imag[j] -= z[j] * np.sin(2.0 * 3.14 * i * j/myN)	
	# print("z finished")

	

	plotComplex3D(xc, yc, zc)

	# plot3D(xc, yc ,zc)

	# print(xc)
	# print(yc)
	# print(zc)




		
	    
	    



if __name__ == '__main__':
	main()
