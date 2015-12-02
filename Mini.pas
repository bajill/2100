/* Et minimalt Pascal-program */
program Mini;
const v1 = 1071; v2 = 462;
var i : integer;
function func1(a: integer; b: integer): integer;
begin
    if a <> b then
    func1 := v1 + a
    else
    func1 := func1(4, 5)
end;
begin
    i := func1(2, v2);    
end.

