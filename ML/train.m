function theta = train(P)
%P is m*n

n = size(P,2);
m = size(P,1);
iter = 1500;
alpha = 0.01;
theta = zeros(n.n-1);
X = zeros(m,n-1);
for i = 1:n
	y = P(:,i);
	if(i==1)
		X = P(:,2:n);
	else if(i==n)
		X = P(:,1:n-1);
	else
		X= P(:,1:i);
		X = [P X(:,i+1:n)];
	endif
	theta(i,:) = (gradDesc(X,y,theta(i,:)',alpha,iter))'
endfor

end

