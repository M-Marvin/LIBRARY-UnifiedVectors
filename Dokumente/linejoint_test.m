close all; clear all;

% line paramters
P1 = [0, 20, 20];
V1 = [40, 0, 0];
P2 = [20, 0, 40];
V2 = [0, 40, 0];
[T1a, T2a] = test(P1, P2, V1, V2);

% line paramters
P1 = [0, 20, 10];
V1 = [40, 0, 20];
P2 = [20, 0, 30];
V2 = [0, 40, 20];
[T1b, T2b] = test(P1, P2, V1, V2);

% line paramters
P1 = [0, 20, 10];
V1 = [40, 0, 40];
P2 = [20, 0, 30];
V2 = [0, 40, 40];
[T1c, T2c] = test(P1, P2, V1, V2);

%% test plot for line
function [T1, T2] = test(P1, P2, V1, V2)
    [PI, VI] = linejoint(P1, P2, V1, V2);
    
    % plot
    quiver3(0, 0, 0, P1(1), P1(2), P1(3), 'off', 'm');
    hold on;
    quiver3(0, 0, 0, P2(1), P2(2), P2(3), 'off', 'm');
    quiver3(P1(1), P1(2), P1(3), V1(1), V1(2), V1(3), 'off', 'b');
    quiver3(P2(1), P2(2), P2(3), V2(1), V2(2), V2(3), 'off', 'b');
    quiver3(0, 0, 0, PI(1), PI(2), PI(3), 'off', 'g');
    quiver3(PI(1), PI(2), PI(3), VI(1), VI(2), VI(3), 'off', 'r');
    axis equal

    T1 = dot(VI, V1);
    T2 = dot(VI, V2);
end