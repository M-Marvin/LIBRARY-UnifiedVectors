clear all; close all;
global p1 p2 p3 P V

P = [0.5, 0.5, 0.5];   % Start point of the line vector
V = [1, 2, 1];         % Line vector
p1 = [2, 0, 0];        % First point on the plane
p2 = [0, 2, 0];        % Second point on the plane
p3 = [0, 0, 2];        % Thrid point on the plane
test_intersect();

P = [0.5, 2.8, 0.5];   % Start point of the line vector
V = [1, -2, -1];       % Line vector
test_intersect();

function test_intersect()
    global p1 p2 p3 P V

    % Get intersection point
    t = intersect(p1, p2, p3, P, V);
    S = P+t*V;
    
    % plot
    quiver3(0, 0, 0, P(1), P(2), P(3), 'off', 'm');
    hold on;
    quiver3(P(1), P(2), P(3), V(1), V(2), V(3), 'off', 'b');
    plotLine(p1, p2);
    plotLine(p2, p3);
    plotLine(p3, p1);
    quiver3(0, 0, 0, S(1), S(2), S(3), 'off', 'p');
    axis equal
end

function plotLine(P1, P2)
    quiver3(P1(1), P1(2), P1(3), P2(1) - P1(1), P2(2) - P1(2), P2(3) - P1(3), 'off', 'r');
end
