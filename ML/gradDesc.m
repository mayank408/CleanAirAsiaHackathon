function theta = gradDesc(X,y,theta,alpha,iter)
M = length(y);
for iter = 1:iter
	sumvar = sum(((X*theta-y).*X)' , 2);
    delta = (1/m)*sumvar;
    theta = theta - alpha*delta;
endfor
end