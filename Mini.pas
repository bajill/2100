/* Et minimalt Pascal-program */
program Mini;
type bool = boolean;
var x : boolean;
y : boolean;

procedure TestBinaryBoolean;

   procedure Test (x: bool;  y: bool);
   begin
      write(x, ' and ', y, ' = ', x and y, eol);
      write(x, ' or ', y, ' = ', x or y, eol);
   end; { Test }

begin
   Test(false, false);  Test(false, true);
   Test(true, false);  Test(true, true);
end; { TestBinaryBoolean }
begin
    TestBinaryBoolean;
    end.
