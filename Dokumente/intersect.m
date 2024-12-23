function [t] = intersect(p1, p2, p3, P, V)
    % Get plane normal
    n = cross(p2 - p1, p3 - p1);
    n = n / norm(n);

    % Get position of intersection on line
    t = (dot(p1, n)-dot(P, n))/dot(V, n);
end
