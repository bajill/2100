/* Et minimalt Pascal-program */
program Mini;
var i : integer;
function func1(a: integer): integer;
begin
    func1 := a;
end;
procedure proc2(a: integer);
begin
    i := a;
end;
begin
    i := func1(2);    
    proc2(4);    
end.

