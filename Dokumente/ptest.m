
close all; clear all;

V1 = [1 0 0];
R = [ V1(1)*7 V1(2)*-11 V1(3)*31 ];
V2 = cross(R, V1);
V2 = V2 / norm(V2);

quiver3(0, 0, 0, V1(1), V1(2), V1(3), 'off');
hold on;
quiver3(0, 0, 0, V2(1), V2(2), V2(3), 'off');
axis equal;