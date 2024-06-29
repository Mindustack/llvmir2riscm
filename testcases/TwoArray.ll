; ModuleID = 'TwoArray.c'
source_filename = "TwoArray.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca [10 x [20 x i32]], align 4
  %3 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 800, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %3) #2
  %4 = getelementptr inbounds [10 x [20 x i32]], ptr %2, i32 0, i32 2
  %5 = getelementptr inbounds [20 x i32], ptr %4, i32 0, i32 3
  store i32 5, ptr %5, align 4, !tbaa !3
  %6 = getelementptr inbounds [10 x [20 x i32]], ptr %2, i32 0, i32 1
  %7 = getelementptr inbounds [20 x i32], ptr %6, i32 0, i32 2
  %8 = load i32, ptr %7, align 4, !tbaa !3
  store i32 %8, ptr %3, align 4, !tbaa !3
  call void @llvm.lifetime.end.p0(i64 4, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 800, ptr %2) #2
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
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
