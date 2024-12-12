%% Autor: M_Marvin
%%
%% calculate shortest line between two other arbitrary lines in 3D space
%% represented by the equations L1(s) = P1 + s * V1 and L2(s) = P2 + s * V2
%% returns two points at the position of the two lines intersecting with the shortest, prependicular line
%%
%% [PI, VI] = linejoint(P1, P2, V1, V2)
function [PI1, VI] = linejoint(P1, P2, V1, V2)
    [a, b] = linejointF(P1, P2, V1, V2);
    
    % construct line
    PI1 = P1 + a*V1;
    PI2 = P2 + b*V2;
    VI = PI2 - PI1;
end

%% calculate indecies
function [a, b] = linejointF(P1, P2, V1, V2)
    S1 = dot(P2, V1) - dot(P1, V1);
    S2 = dot(P2, V2) - dot(P1, V2);
    S3 = norm(V2)^2;
    S4 = norm(V1)^2;
    S5 = dot(V2, V1);
    a = (S1*S3-S2*S5)/(S3*S4-S5^2);
    b = -(S2*S4-S1*S5)/(S3*S4-S5^2);
end
