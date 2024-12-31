; ModuleID = 'Number_array_definition.c'
source_filename = "Number_array_definition.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca [4 x [5 x i32]], align 4
  %3 = alloca [5 x i32], align 4
  %4 = alloca [2 x [2 x [2 x [2 x [2 x [2 x [2 x i32]]]]]]], align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 80, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 20, ptr %3) #2
  call void @llvm.lifetime.start.p0(i64 512, ptr %4) #2
  call void @llvm.lifetime.end.p0(i64 512, ptr %4) #2
  call void @llvm.lifetime.end.p0(i64 20, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 80, ptr %2) #2
  ret i32 0
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #1

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #1

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #1 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #2 = { nounwind }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
